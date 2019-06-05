package com.github.marocraft.trackntrace.http;

import java.io.IOException;
import java.util.Collection;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.github.marocraft.trackntrace.config.IConfigurationTnT;
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
public class CorrelationIdInterceptor implements Filter {

	@Autowired
	IHttpLog httpLog;

	@Autowired
	@Qualifier("configurationTnTDefault")
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

//			//wrapper
//			ResponseRequestWrapper responseRequestWrapper= new ResponseRequestWrapper(httpServletRequest);
//			
//			//all headers
//			Collection<String> respheaders = httpServletResponse.getHeaderNames();
//			respheaders.forEach(header->System.out.println("resp all headers before: "+header));
//			
//			Enumeration<String> reqheaders = httpServletRequest.getHeaderNames();
//			while (reqheaders.hasMoreElements()) {
//				String header = (String) reqheaders.nextElement();
//				System.out.println("req all headers  before: "+header);
//				
//			}
//			
//			String traceIdReq= httpServletRequest.getHeader("x-b3-traceid");
//			if(!StringUtils.isEmpty(traceIdReq)) {
//				//responseRequestWrapper.addHeader("x-b3-traceid", "2222");
//				System.out.println("req trace id: "+traceIdReq);
//			}
//			
//			String traceIdRes= httpServletResponse.getHeader("x-b3-traceid");
//			if(!StringUtils.isEmpty(traceIdRes)) {
//				
//				System.out.println("res trace id: "+traceIdRes);
//			}else {
//				httpServletResponse.addHeader("x-b3-traceid", "222");
//				System.out.println("correlationid added: 222");
//			}
//			
//			
//			if(!StringUtils.isEmpty(traceIdReq)) {
//				System.out.println("req trace id: "+traceIdReq);
//			}
//				
//			if(!StringUtils.isEmpty(traceIdRes)) {
//				System.out.println("res trace id: "+traceIdRes);
//			}
//			//all headers
//			Collection<String> respheaders2 = httpServletResponse.getHeaderNames();
//			respheaders2.forEach(header->System.out.println("resp all headers: "+header));
//			
//			Enumeration<String> reqheaders2 = httpServletRequest.getHeaderNames();
//			while (reqheaders2.hasMoreElements()) {
//				String header = (String) reqheaders2.nextElement();
//				System.out.println("req all headers: "+header);
//				
//			}
			chain.doFilter(httpServletRequest, httpServletResponse);
		}

	}



	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// Ignore
	}
}