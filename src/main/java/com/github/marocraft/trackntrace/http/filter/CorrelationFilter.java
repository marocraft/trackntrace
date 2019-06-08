/**
 * 
 */
package com.github.marocraft.trackntrace.http.filter;

import java.io.IOException;
import java.util.Enumeration;
import java.util.UUID;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.github.marocraft.trackntrace.config.IConfigurationTnT;
import com.github.marocraft.trackntrace.domain.CorrelationId;
import com.github.marocraft.trackntrace.utils.CommonUtils;

/**
 * 
 *
 */
@Component
@Order(1)
public class CorrelationFilter implements Filter {
	@Autowired
	CorrelationId correlationId;

	public static final String CORRELATION_HEADER_NAME = "x-b3-traceid";
	public static final String SPAN_ID = "x-b3-spanid";
	public static final String PARENT_ID = "x-b3-parentspanid";
	public static final String SAMPLED_STATE = "x-b3-sampled";
	
	@Autowired
	@Qualifier("configurationTnTRest")
	IConfigurationTnT config;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		String id = CommonUtils.toLowerHex(CommonUtils.nextId());
		String traceId = httpServletRequest.getHeader(config.getTraceIdName());
		if (StringUtils.isEmpty(traceId)) {
			traceId = id;
		}
		correlationId.setTraceId(traceId);
		String spanId = httpServletRequest.getHeader(config.getSpanIdName());
		if (!StringUtils.isEmpty(spanId)) {
			correlationId.setParentId(spanId);

		} else {
			correlationId.setParentId(id);
		}
		correlationId.setSpanId(CommonUtils.toLowerHex(CommonUtils.nextId()));
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void destroy() {
	}
}