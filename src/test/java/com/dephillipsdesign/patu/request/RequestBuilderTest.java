package com.dephillipsdesign.patu.request;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

import com.dephillipsdesign.patu.http.request.Request;
import com.dephillipsdesign.patu.http.request.RequestBuilder;

public class RequestBuilderTest {

	@Test
	public void buildsGetRequest() {
		Request request = new RequestBuilder(Arrays.asList("GET /whats_new.html HTTP/1.0")).build();
		assertEquals("GET", request.getMethod());
		assertEquals("/whats_new.html", request.getPath());
		assertEquals("1.0", request.getHttpVersion());
	}
	
	@Test(expected=UnsupportedOperationException.class)
	public void buldingPostRequestFails() {
		RequestBuilder requestBuilder = new RequestBuilder(Arrays.asList("POST / HTTP/1.0"));
		requestBuilder.build();
	}
	
	@Test
	public void testBuiltRequestIncludesHeader() {
		
	}
	
	
}
