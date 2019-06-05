/**
 * 
 */
package com.github.marocraft.trackntrace.http.interceptor;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import com.github.marocraft.trackntrace.http.bean.CorrelationId;
import com.github.marocraft.trackntrace.http.filter.CorrelationFilter;

/**
 * 
 *
 */
@Component
@Order(2)
public class CorrelationInterceptor implements ClientHttpRequestInterceptor {

	@Autowired
	CorrelationId correlationId;

	@Override
	public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
			throws IOException {
		HttpHeaders headers = request.getHeaders();
		headers.set(CorrelationFilter.CORRELATION_HEADER_NAME, correlationId.getTraceId());
		return execution.execute(request, body);
	}
}