// Prevents additional console window on Windows in release, DO NOT REMOVE!!
#![cfg_attr(not(debug_assertions), windows_subsystem = "windows")]

use futures::SinkExt;
use futures::StreamExt;
use futures_util::future;
use futures_util::stream::SplitSink;
use std::sync::Arc;
use tauri::window;
use tokio::io::AsyncReadExt;
use tokio::net::TcpStream;
use tokio::sync::Mutex;
use tokio_tungstenite::tungstenite::Message;
use tokio_tungstenite::MaybeTlsStream;
use tokio_tungstenite::WebSocketStream;
use url::Url;

const SOCKET_SERVER_ADDR: &str = "ws://127.0.0.1:1024/socket";

type Socket = SplitSink<WebSocketStream<MaybeTlsStream<TcpStream>>, Message>;
struct SocketStoreage {
  stream: Arc<Mutex<Option<Socket>>>,
}

#[tokio::main]
async fn main() {
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
  }

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
  let (ws_write, rx) = ws_stream.split();

  tokio::spawn(async move {
    rx.for_each(|msg| async {
      match msg.unwrap() {
        Message::Text(text) => {
          println!("server message ----->>> {}", text);
          let _ = window.emit("sys_message", text);
          // window.lock().await.emit("event", "a");
        }
        _ => {}
      }
    })
    .await;
  });

  *socket.stream.lock().await = Some(ws_write);

  Ok(())
}

/// 发送消息
#[tauri::command]
async fn send_message<'r>(msg: &str, socket: tauri::State<'r, SocketStoreage>) -> Result<bool, ()> {
  let mut ws_writer = socket.stream.lock().await;
  let writer = ws_writer.as_mut().unwrap();

  match writer.send(Message::Text(msg.to_string())).await {
    Ok(_) => Ok(true),
    Err(_) => Ok(false),
  }
}
