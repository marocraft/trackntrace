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
	Correlater correlator;

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

			processTraceId(httpServletRequest, configTnt.getTraceidName());
			processSpanId(httpServletRequest, configTnt.getSpanIdName());

			httpServletResponse.setHeader(configTnt.getTraceidName(), correlator.getTraceId());
			httpServletResponse.setHeader(configTnt.getSpanIdName(), correlator.getSpanId());
		}

		// continue
		chain.doFilter(request, response);
	}

	/**
	 * Create traceId if it does not exist
	 * 
	 * @param headerId
	 * @param request
	 */
	public void processTraceId(HttpServletRequest request, String headerId) {
		if (request.getHeader(headerId) == null) {
			correlator.setTraceId(commonUtils.uniqid());
		} else {
			correlator.setTraceId(request.getHeader(headerId));
		}
	}

	/**
	 * Create sapnid if it does not exist
	 * 
	 * @param headerId
	 * @param request
	 */
	public void processSpanId(HttpServletRequest request, String headerId) {
		if (request.getHeader(headerId) == null) {
			correlator.setSpanId(commonUtils.uniqid());
		} else {
			correlator.setSpanId(request.getHeader(headerId));
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// Ignore
	}
}