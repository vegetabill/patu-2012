package com.dephillipsdesign.patu.http.request;

import java.util.Map;

import com.google.common.collect.Maps;

public abstract class Request {

  protected final Map<String, String> headers = Maps.newHashMap();
  protected final String path;
  protected final String body;
  private final String httpVersion;

  protected Request(String path, String httpVersion) {
    this.body = "";
    this.path = path;
    this.httpVersion = httpVersion;
  }

  public abstract String getMethod();

  public String toString() {
    return String.format("HTTP/%s %s %s", getHttpVersion(), getMethod(),
        getPath());
  }

  public String getPath() {
    return this.path;
  }

  public String getHttpVersion() {
    return this.httpVersion;
  }

  public Map<String, String> getHeaders() {
    return this.headers;
  }

}
