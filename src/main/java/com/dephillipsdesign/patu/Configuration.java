package com.dephillipsdesign.patu;

import com.dephillipsdesign.lychee.host.HostOperatingSystem;
import com.google.common.base.Objects;

public class Configuration {

  private Configuration() {
  }

  private static final Configuration current = new Configuration();

  public static final Configuration current() {
    return current;
  }

  private String docRoot;

  public String getDocRoot() {
    return Objects.firstNonNull(this.docRoot, HostOperatingSystem.getHostOS()
        .getDefaultDocRoot().orNull());
  }

  public void setDocRoot(String docRoot) {
    this.docRoot = docRoot;
  }

}
