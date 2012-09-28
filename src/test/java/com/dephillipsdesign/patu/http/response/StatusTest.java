package com.dephillipsdesign.patu.http.response;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class StatusTest {

  @Test
  public void reasonPhraseIsCorrectlyCased() {
    assertThat(Status.NOT_FOUND.getReasonPhrase(), is("Not Found"));
  }

  @Test
  public void okIsAllCaps() {
    assertThat(Status.OK.getReasonPhrase(), is("OK"));
  }

}
