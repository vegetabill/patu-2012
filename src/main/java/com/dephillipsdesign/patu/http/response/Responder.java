package com.dephillipsdesign.patu.http.response;

import com.dephillipsdesign.patu.http.request.Request;

public interface Responder {

  public Response respond(Request request);

}
