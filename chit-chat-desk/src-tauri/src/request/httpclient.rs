use std::{io, sync::Arc};

use futures_util::lock::Mutex;
use serde::Serializer;
use tokio::net::TcpStream;

pub struct HttpClient {
  stream: Arc<Mutex<Option<TcpStream>>>,
  url: Arc<Mutex<Option<String>>>,
}

impl HttpClient {
  pub fn new() -> Self {
    Self {
      url: Arc::new(Mutex::default()),
      stream: Arc::new(Mutex::default()),
    }
  }

  pub async fn create(self, addr: &str) -> io::Result<Self> {
    let stream = TcpStream::connect(addr).await?;

    *self.stream.lock().await = Some(stream);
    *self.url.lock().await = Some(String::from(addr));

    Ok(self)
  }

  pub async fn get(&self, path: &str) -> HttpClientRequest {
    HttpClientRequest::new(self.url.lock().await.as_ref().unwrap(), path)
  }
}

struct HttpClientRequest {
  url: String,
  path: String,
}

impl HttpClientRequest {
  pub fn new(url: &str, path: &str) -> Self {
    Self {
      url: String::from(url),
      path: String::from(path),
    }
  }

  pub fn set_cookie<T: Serializer>(cookie: T) {}
}
