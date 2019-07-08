package com.github.marocraft.trackntrace.http.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.github.marocraft.trackntrace.config.IConfigurationTnT;
import com.github.marocraft.trackntrace.http.IHttpLog;
import com.github.marocraft.trackntrace.utils.CommonUtils;

/**
 * Filter http that handle correlation ids. If no correlation ids are found, the
 * handler create them
 * 
 * @author Houseine TASSA
 * @author Khalid ELABBADI
 *
 */
@Component
public class HttpLogsFilter implements Filter {

	@Autowired
	IHttpLog httpLog;

	@Autowired
	@Qualifier("configurationTnTRest")
	IConfigurationTnT configTnt;

	@Autowired
	CommonUtils commonUtils;

	@Override
	public void destroy() {
		// Ignore
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		if (request instanceof HttpServletRequest && response instanceof HttpServletResponse) {

			HttpServletRequest httpServletRequest = (HttpServletRequest) request;
			HttpServletResponse httpServletResponse = (HttpServletResponse) response;

			httpLog.setHttpVerb(httpServletRequest.getMethod());
			httpLog.setHttpStatus(httpServletResponse.getStatus() + "");
			String uri = httpServletRequest.getRequestURI();
			if (httpServletRequest.getQueryString() != null) {
				uri = uri + "?" + httpServletRequest.getQueryString();
			}
			httpLog.setHttpURI(uri);
			httpLog.setIp(getPublicIpAdress(httpServletRequest));
			chain.doFilter(httpServletRequest, httpServletResponse);
		}

	}

	public String getPublicIpAdress(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// Ignore
	}
}