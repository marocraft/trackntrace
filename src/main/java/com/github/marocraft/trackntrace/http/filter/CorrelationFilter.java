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
	CorrelationId correlationId;

	public static final String CORRELATION_HEADER_NAME = "x-b3-traceid";
	public static final String SPAN_ID = "x-b3-spanid";
	public static final String PARENT_ID = "x-b3-parentspanid";
	public static final String SAMPLED_STATE = "x-b3-sampled";

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest httpServletRequest = (HttpServletRequest) request;

		String id = toLowerHex(nextId());

		String traceId = httpServletRequest.getHeader(CORRELATION_HEADER_NAME);
		if (StringUtils.isEmpty(traceId)) {
			traceId = id;
		}

		correlationId.setTraceId(traceId);
		String spanId = httpServletRequest.getHeader(SPAN_ID);
		if (!StringUtils.isEmpty(spanId)) {
			correlationId.setParentId(spanId);
			
		} else {
			correlationId.setParentId(id);
			
		}
		correlationId.setSpanId(toLowerHex(nextId()));
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void destroy() {
	}

	long nextId() {
		long nextId = randomLong();
		while (nextId == 0L) {
			nextId = randomLong();
		}
		return nextId;
	}

	public long randomLong() {
		return java.util.concurrent.ThreadLocalRandom.current().nextLong();
	}

	/** Inspired by {@code okio.Buffer.writeLong} */
	public static String toLowerHex(long v) {
		char[] data = new char[16];
		writeHexLong(data, 0, v);
		return new String(data);
	}

	/** Inspired by {@code okio.Buffer.writeLong} */
	public static void writeHexLong(char[] data, int pos, long v) {
		writeHexByte(data, pos + 0, (byte) ((v >>> 56L) & 0xff));
		writeHexByte(data, pos + 2, (byte) ((v >>> 48L) & 0xff));
		writeHexByte(data, pos + 4, (byte) ((v >>> 40L) & 0xff));
		writeHexByte(data, pos + 6, (byte) ((v >>> 32L) & 0xff));
		writeHexByte(data, pos + 8, (byte) ((v >>> 24L) & 0xff));
		writeHexByte(data, pos + 10, (byte) ((v >>> 16L) & 0xff));
		writeHexByte(data, pos + 12, (byte) ((v >>> 8L) & 0xff));
		writeHexByte(data, pos + 14, (byte) (v & 0xff));
	}

	static final char[] HEX_DIGITS = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

	static void writeHexByte(char[] data, int pos, byte b) {
		data[pos + 0] = HEX_DIGITS[(b >> 4) & 0xf];
		data[pos + 1] = HEX_DIGITS[b & 0xf];
	}
}