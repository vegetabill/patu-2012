package com.dephillipsdesign.patu.http.response;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Map;

import com.google.common.collect.Maps;

public class Response {

  private Status status;
  private final Map<String, String> headers = Maps.newHashMap();
  private final String httpVersion = "1.0";
  private StringWriter bodyWriter = new StringWriter();

  public String getHttpVersion() {
    return httpVersion;
  }

  public PrintWriter getBodyWriter() {
    return new PrintWriter(this.bodyWriter);
  }

  public Status getStatus() {
    return status;
  }

  public void setStatus(Status status) {
    this.status = status;
  }

  public void clearBody() {
    this.bodyWriter = new StringWriter();
  }

  public String asString() {
    StringWriter rawWriter = new StringWriter();
    PrintWriter responseWriter = new PrintWriter(rawWriter);
    responseWriter.println(String.format("HTTP/%s %d %s", getHttpVersion(),
        status.getStatusCode(), status.getReasonPhrase()));
    responseWriter.print(bodyWriter.toString());
    responseWriter.println();
    return rawWriter.toString();
  }

}
