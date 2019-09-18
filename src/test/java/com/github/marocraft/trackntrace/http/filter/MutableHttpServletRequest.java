/**
 * 
 */
package com.github.marocraft.trackntrace.http.filter;

import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * 
 *
 */
final class MutableHttpServletRequest extends HttpServletRequestWrapper {
	private final Map<String, String> customHeaders;

	public MutableHttpServletRequest(HttpServletRequest request) {
		super(request);
		this.customHeaders = new HashMap<String, String>();
	}

	public void putHeader(String name, String value) {
		this.customHeaders.put(name, value);
	}

	public String getHeader(String name) {
		String headerValue = customHeaders.get(name);

		if (headerValue != null) {
			return headerValue;
		}
		return ((HttpServletRequest) getRequest()).getHeader(name);
	}

	public Enumeration<String> getHeaderNames() {
		Set<String> set = new HashSet<String>(customHeaders.keySet());

		@SuppressWarnings("unchecked")
		Enumeration<String> e = ((HttpServletRequest) getRequest()).getHeaderNames();
		while (e.hasMoreElements()) {
			String n = e.nextElement();
			set.add(n);
		}

		return Collections.enumeration(set);
	}
}
