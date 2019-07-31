package com.github.marocraft.trackntrace.http;

public interface IHttpLog {
	public String getHttpVerb();

	public void setHttpVerb(String httpVerbName);

	public String getHttpURI();

	public void setHttpURI(String httpURI);

	public String getHttpStatus();

	public void setHttpStatus(String httpStatus);

	public String getIp();

	public void setIp(String ip);
}
