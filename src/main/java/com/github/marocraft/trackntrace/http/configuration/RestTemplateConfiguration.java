/**
 * 
 */
package com.github.marocraft.trackntrace.http.configuration;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestTemplate;

import com.github.marocraft.trackntrace.http.interceptor.CorrelationInterceptor;

/**
 * 
 *
 */
@Configuration
public class RestTemplateConfiguration {

	@Autowired (required=false)
	private RestTemplate restTemplate;
	
	@Autowired
	CorrelationInterceptor correlationInterceptor;
	
	@PostConstruct
	public void addInterceptor() {
	if(restTemplate!=null) {
		List<ClientHttpRequestInterceptor> interceptors = restTemplate.getInterceptors();
		interceptors.add(correlationInterceptor);
		restTemplate.setInterceptors(interceptors);
	}
		
	}
}