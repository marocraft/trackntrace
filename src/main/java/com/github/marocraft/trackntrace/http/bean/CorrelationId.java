/**
 * 
 */
package com.github.marocraft.trackntrace.http.bean;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 *
 */
@Getter
@Setter
@Component
@Scope(scopeName="request",proxyMode=ScopedProxyMode.TARGET_CLASS)
public class CorrelationId {
	private String traceId;
	private String spanId;
	private String parentId;
}
