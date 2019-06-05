/**
 * 
 */
package com.github.marocraft.trackntrace.http.filter;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.github.marocraft.trackntrace.http.bean.CorrelationId;

/**
 * 
 *
 */
@Component
@Order(1)
public class CorrelationFilter implements Filter {
	@Autowired
	CorrelationId traceId;

	public static final String CORRELATION_HEADER_NAME = "CorrelationId";

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		String correlationId = httpServletRequest.getHeader(CORRELATION_HEADER_NAME);

		if (StringUtils.isEmpty(correlationId)) {
			correlationId = UUID.randomUUID().toString();
		}

		traceId.setTraceId(correlationId);
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void destroy() {
	}
}