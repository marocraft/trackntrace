package com.github.marocraft.trackntrace.http;

import org.springframework.stereotype.Component;

@Component
public class HttpLog implements IHttpLog {

	private String httpVerbName;

	private String httpStatus;

	private String httpUri;

	@Override
	public String getHttpVerb() {
		return httpVerbName;
	}

	@Override
	public void setHttpVerb(String httpVerbName) {
		this.httpVerbName = httpVerbName;
	}

	@Override
	public String getHttpURI() {
		return httpUri;
	}

	@Override
	public String getHttpStatus() {
		return httpStatus;
	}

	@Override
	public void setHttpURI(String httpURI) {
		this.httpUri = httpURI;
	}

	@Override
	public void setHttpStatus(String httpStatus) {
		this.httpStatus = httpStatus;
	}

}
