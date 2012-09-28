package com.dephillipsdesign.patu.http.response;

import com.dephillipsdesign.lychee.base.SentenceCaseFormat;
import com.google.common.base.CaseFormat;

public enum Status {

  OK(200) {
    public String getReasonPhrase() {
      return "OK";
    }
  }
  ,
  NOT_FOUND(404), INTERNAL_SERVER_ERROR(500), NOT_IMPLEMENTED(501);

  private int statusCode;

  Status(int statusCode) {
    this.statusCode = statusCode;
  };

  public int getStatusCode() {
    return this.statusCode;
  }

  public String getReasonPhrase() {
    return SentenceCaseFormat.from(CaseFormat.UPPER_UNDERSCORE, name());
  }

}
