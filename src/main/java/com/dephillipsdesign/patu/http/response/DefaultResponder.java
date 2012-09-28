package com.dephillipsdesign.patu.http.response;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;

import com.dephillipsdesign.patu.Configuration;
import com.dephillipsdesign.patu.http.request.Request;
import com.google.common.io.Files;
import com.google.common.io.LineProcessor;

public class DefaultResponder implements Responder {

  public Response respond(Request request) {

    final Response response = new Response();
    final PrintWriter responseWriter = response.getBodyWriter();
    File requestedResource = new File(new File(Configuration.current()
        .getDocRoot()), request.getPath());
    if (requestedResource.exists() && requestedResource.isFile()) {
      responseWriter.println();
      try {
        Files.readLines(requestedResource, Charset.defaultCharset(),
            new LineProcessor<Void>() {
              public Void getResult() {
                return null;
              }

              public boolean processLine(String line) throws IOException {
                responseWriter.println(line);
                return true;
              }

            });
        response.setStatus(Status.OK);
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    } else {
      response.setStatus(Status.NOT_FOUND);
      response.clearBody();
    }

    return response;
  }

}
