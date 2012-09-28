package com.dephillipsdesign.patu;

import com.dephillipsdesign.patu.net.HttpServer;

public class Main {

  public static void main(String... args) {
    HttpServer server = new HttpServer();
    server.start();
  }

}
