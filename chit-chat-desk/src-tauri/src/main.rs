// Prevents additional console window on Windows in release, DO NOT REMOVE!!
#![cfg_attr(not(debug_assertions), windows_subsystem = "windows")]

use futures::SinkExt;
use futures::StreamExt;
use futures_util::stream::SplitStream;
use std::sync::Arc;
use tauri::window;
use tauri::Manager;
use tokio::io::AsyncReadExt;
use tokio::net::TcpStream;
use tokio::sync::Mutex;
use tokio_tungstenite::tungstenite::Message;
use tokio_tungstenite::MaybeTlsStream;
use tokio_tungstenite::WebSocketStream;
use url::Url;

const SOCKET_SERVER_ADDR: &str = "ws://127.0.0.1:1024/socket";

type Socket = WebSocketStream<MaybeTlsStream<TcpStream>>;
struct SocketStoreage {
  stream: Arc<Mutex<Option<Socket>>>,
}

#[tokio::main]
async fn main() {
  println!("app before running...");
  tauri::Builder::default()
    .manage(SocketStoreage {
      stream: Arc::new(Mutex::new(None)),
    })
    .invoke_handler(tauri::generate_handler![
      connect_socket,
      check_socket,
      send_message
    ])
    .run(tauri::generate_context!())
    .expect("error while running tauri application");
}

/// 检查 socket 是否连接
#[tauri::command]
async fn check_socket<'r>(socket: tauri::State<'r, SocketStoreage>) -> Result<bool, ()> {
  if socket.stream.lock().await.is_some() {
    return Ok(true);
  };
  Ok(false)
}

/// 连接 socket
#[tauri::command]
async fn connect_socket<'r>(
  window: tauri::Window,
  socket: tauri::State<'r, SocketStoreage>,
) -> Result<(), ()> {
  let url = Url::parse(&SOCKET_SERVER_ADDR).expect("parse failed");
  let (ws_stream, _) = tokio_tungstenite::connect_async(url).await.unwrap();

  *socket.stream.lock().await = Some(ws_stream);
  tokio::spawn(handle_messages(socket.stream.clone()));

  Ok(())
}

/// 监听服务端消息
async fn handle_messages(socket: Arc<Mutex<Option<Socket>>>) {
  let mut ws_stream = socket.lock().await;
  let (_, mut read) = ws_stream.as_mut().unwrap().split();

  while let Some(Ok(msg)) = read.next().await {
    // 处理接收到的消息，这里只是简单地打印
    match msg {
      Message::Text(text) => println!("Received text message: {}", text),
      Message::Binary(bin) => println!("Received binary message with {} bytes", bin.len()),
      Message::Close(_) => {
        println!("Received close message, closing connection.");
        break;
      }
      _ => {}
    }
  }
}

/// 发送消息
#[tauri::command]
async fn send_message<'r>(msg: &str, socket: tauri::State<'r, SocketStoreage>) -> Result<bool, ()> {
  let mut ws_stream = socket.stream.lock().await;
  let (mut write, _) = ws_stream.as_mut().unwrap().split();

  match write.send(Message::Text(msg.to_string())).await {
    Ok(_) => Ok(true),
    Err(_) => Ok(false),
  }
}
