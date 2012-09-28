package com.dephillipsdesign.patu.net;

import java.io.IOException;
import java.net.ServerSocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dephillipsdesign.patu.Configuration;

public class HttpServer implements Runnable {

  private Thread thread;
  private ServerSocket server;
  private final Logger LOG = LoggerFactory.getLogger(getClass());

  public void start() {
    this.thread = new Thread(this, "ServerSocketListenerThread");
    this.thread.start();
    try {
      this.thread.join();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public void run() {
    LOG.info("starting patu version 0.0.1");
    LOG.info("serving docs from {}", Configuration.current().getDocRoot());
    try {
      server = new ServerSocket(8080);
      LOG.info("started listening on 8080");
      while (true) {
        new ClientConnectionHandler(server.accept()).handle();
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    } finally {
      if (server != null) {
        try {
          server.close();
        } catch (IOException e) {
          throw new RuntimeException(e);
        }
      }
    }
  }

  public void stop() {
    this.thread.interrupt();
  }

}
