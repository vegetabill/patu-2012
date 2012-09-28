package com.dephillipsdesign.patu.http.response;

import com.google.common.base.CaseFormat;

public enum Status {

  OK(200), NOT_FOUND(404), INTERNAL_SERVER_ERROR(500), NOT_IMPLEMENTED(501);

  private int statusCode;

  Status(int statusCode) {
    this.statusCode = statusCode;
  };

  public int getStatusCode() {
    return this.statusCode;
  }

  // Optional.of(T)

  public String getReasonPhrase() {
    // CaseFormat.UPPER_UNDERSCORE.to(CaseFormat., "CONSTANT_NAME")); //
    // returns "constantName"

    String camelized = CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, name());

    return name();
  }

}
