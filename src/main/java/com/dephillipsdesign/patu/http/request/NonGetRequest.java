package com.dephillipsdesign.patu.http.request;

public class NonGetRequest extends Request {

  private final String method;

  protected NonGetRequest(String method, String path, String httpVersion) {
    super(path, httpVersion);
    this.method = method;
  }

  public String getMethod() {
    return this.method;
  }

}
