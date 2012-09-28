package com.dephillipsdesign.patu.http.request;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Preconditions;
import com.google.common.base.Splitter;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.Lists;

public class RequestBuilder {

  private final Logger LOG = LoggerFactory.getLogger(getClass());
  private final List<String> clientMessage;

  public RequestBuilder(List<String> clientMessage) {
    this.clientMessage = clientMessage;
  }

  public RequestBuilder(InputStream inputStream) {
    this.clientMessage = Lists.newArrayList();
    BufferedReader reader = new BufferedReader(new InputStreamReader(
        inputStream));
    String line = "";
    try {
      while (!(line = reader.readLine()).isEmpty()) {
        LOG.debug(line);
        this.clientMessage.add(line);
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public Request build() {
    FluentIterable<String> lines = FluentIterable.from(this.clientMessage);
    String firstLine = lines.first().get();
    FluentIterable<String> components = FluentIterable.from(Splitter.on(" ")
        .split(firstLine));
    Preconditions.checkState(components.size() == 3, String.format(
        "Unable to understand '%s', expected valid HTTP Request", firstLine));
    String method = components.get(0);
    String path = components.get(1);
    String httpVersion = extractHttpVersion(components.get(2));

    if (!method.equals("GET")) {
      throw new UnsupportedOperationException("Request method of " + method
          + " not supported");
    }

    GetRequest getRequest = new GetRequest(path, httpVersion);
    return getRequest;
  }

  private String extractHttpVersion(String versionIdentifier) {
    return FluentIterable.from(Splitter.on("/").split(versionIdentifier))
        .get(1);
  }

}
