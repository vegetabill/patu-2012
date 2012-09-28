package com.dephillipsdesign.patu.net;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dephillipsdesign.patu.http.request.Request;
import com.dephillipsdesign.patu.http.request.RequestBuilder;
import com.dephillipsdesign.patu.http.response.DefaultResponder;
import com.dephillipsdesign.patu.http.response.EmptyResponse;
import com.dephillipsdesign.patu.http.response.InternalServerError;
import com.dephillipsdesign.patu.http.response.Response;
import com.google.common.base.Preconditions;
import com.google.common.io.CharStreams;
import com.google.common.io.OutputSupplier;

public class ClientConnectionHandler implements Runnable {

  private final Logger LOG = LoggerFactory.getLogger(getClass());
  private final Socket clientSocket;

  public ClientConnectionHandler(Socket clientSocket) {
    Preconditions.checkNotNull(clientSocket);
    this.clientSocket = clientSocket;
  }

  public void handle() {
    Thread t = new Thread(this);
    t.start();
    LOG.debug("Client {} being handled by {}",
        this.clientSocket.getRemoteSocketAddress(), t.getName());
  }

  public void run() {
    Response response = new EmptyResponse();
    try {
      RequestBuilder requestBuilder = new RequestBuilder(
          this.clientSocket.getInputStream());
      Request request = requestBuilder.build();
      response = new DefaultResponder().respond(request);
      sendToClient(response);
    } catch (Exception e) {
      response = new InternalServerError(e);
    } finally {
      LOG.debug("Sending {} to {}", response,
          clientSocket.getRemoteSocketAddress());
      try {
        LOG.debug("Closing {}", clientSocket.getRemoteSocketAddress());
        clientSocket.close();
      } catch (IOException e) {
        LOG.error("Unable to close: {}", clientSocket.getRemoteSocketAddress());
      }
    }

  }

  private void sendToClient(Response response) {
    try {
      String responseMessage = response.asString();
      CharStreams.copy(CharStreams.newReaderSupplier(responseMessage),
          new OutputSupplier<Writer>() {
            public Writer getOutput() throws IOException {
              return new OutputStreamWriter(clientSocket.getOutputStream());
            }
          });
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

}
