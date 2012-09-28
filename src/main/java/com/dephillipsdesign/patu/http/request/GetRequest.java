package com.dephillipsdesign.patu.http.request;

public class GetRequest extends Request {

  public GetRequest(String path, String httpVersion) {
    super(path, httpVersion);
  }

  public String getMethod() {
    return "GET";
  }

}
