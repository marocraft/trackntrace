/**
 * 
 */
package com.github.marocraft.trackntrace.http.interceptor;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import com.github.marocraft.trackntrace.config.IConfigurationTnT;
import com.github.marocraft.trackntrace.domain.CorrelationId;
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

	@Autowired
	@Qualifier("configurationTnTRest")
	IConfigurationTnT configTnt;

	@Override
	public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
			throws IOException {
		HttpHeaders headers = request.getHeaders();
		headers.set(configTnt.getTraceIdName(), correlationId.getTraceId());
		headers.set(configTnt.getSpanIdName(), correlationId.getSpanId());
		headers.set("x-b3-sampled", "0");
		headers.set(configTnt.getParentSpanIdName(), correlationId.getParentId());
		return execution.execute(request, body);
	}
}