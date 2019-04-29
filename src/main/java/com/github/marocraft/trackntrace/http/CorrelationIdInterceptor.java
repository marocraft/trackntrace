package com.github.marocraft.trackntrace.http;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.marocraft.trackntrace.enums.CorrelationName;
import com.github.marocraft.trackntrace.utils.CommonUtils;

@Component
public class CorrelationIdInterceptor implements Filter {

	@Autowired
	Correlater httpRequestStatus;

	@Autowired
	CommonUtils commonUtils;

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		if (request instanceof HttpServletRequest && response instanceof HttpServletResponse) {
			HttpServletRequest httpServletRequest = (HttpServletRequest) request;
			HttpServletResponse httpServletResponse = (HttpServletResponse) response;

			if (httpServletRequest.getHeader(CorrelationName.TRACE_ID) == null) {
				// create tracer id
				httpRequestStatus.setTraceId(commonUtils.uniqid());
				httpServletResponse.setHeader(CorrelationName.TRACE_ID, httpRequestStatus.getTraceId());
			} else {
				httpRequestStatus.setTraceId(httpServletRequest.getHeader(CorrelationName.TRACE_ID));
			}

			if (httpServletRequest.getHeader(CorrelationName.SPAN_ID) == null) {
				// create span id
				httpRequestStatus.setSpanId((commonUtils.uniqid()));
				httpServletResponse.setHeader(CorrelationName.SPAN_ID, httpRequestStatus.getSpanId());
			} else {
				httpRequestStatus.setSpanId(httpServletRequest.getHeader(CorrelationName.SPAN_ID));
			}

		}
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}

}
