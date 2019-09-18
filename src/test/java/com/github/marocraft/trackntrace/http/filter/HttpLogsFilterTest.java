/**
 * 
 */
package com.github.marocraft.trackntrace.http.filter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.github.marocraft.trackntrace.context.SpringAOPContext;

/**
 * 
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringAOPContext.class)
public class HttpLogsFilterTest {
	HttpServletRequest httpServletRequest;
	HttpServletResponse httpServletResponse;
	HttpLogsFilter httpLogsFilter;
	TestServlet servlet;
	

	@Autowired
	HttpLogsFilter filter;

	MutableHttpServletRequest mutableHttpServletRequest;

	@Before
	public void before() {
		httpServletRequest = Mockito.mock(HttpServletRequest.class);
		httpServletResponse = Mockito.mock(HttpServletResponse.class);
		httpLogsFilter = new HttpLogsFilter();
		servlet = new TestServlet();

	}

	/**
	 * Test method for
	 * {@link com.github.marocraft.trackntrace.http.filter.HttpLogsFilter#getPublicIpAdress(javax.servlet.http.HttpServletRequest)}.
	 */
	@Test
	public void shouldGetPublicIpAdressFromXForwardedFor() {
		mutableHttpServletRequest = new MutableHttpServletRequest(httpServletRequest);
		mutableHttpServletRequest.putHeader("x-forwarded-for", "10.78.243.194");
		try {
			servlet.doGet(mutableHttpServletRequest, httpServletResponse);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ServletException e) {
			e.printStackTrace();
		}
		Assert.assertEquals("10.78.243.194", httpLogsFilter.getPublicIpAdress(mutableHttpServletRequest));

	}

	/**
	 * Test method for
	 * {@link com.github.marocraft.trackntrace.http.filter.HttpLogsFilter#getPublicIpAdress(javax.servlet.http.HttpServletRequest)}.
	 */
	@Test
	public void shouldGetPublicIpAdressFromProxyClientIP() {
		mutableHttpServletRequest = new MutableHttpServletRequest(httpServletRequest);
		mutableHttpServletRequest.putHeader("x-forwarded-for", null);
		mutableHttpServletRequest.putHeader("Proxy-Client-IP", "10.78.243.193");
		try {
			servlet.doGet(mutableHttpServletRequest, httpServletResponse);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ServletException e) {
			e.printStackTrace();
		}

		Assert.assertEquals("10.78.243.193", httpLogsFilter.getPublicIpAdress(mutableHttpServletRequest));
	}

	/**
	 * Test method for
	 * {@link com.github.marocraft.trackntrace.http.filter.HttpLogsFilter#getPublicIpAdress(javax.servlet.http.HttpServletRequest)}.
	 */
	@Test
	public void shouldGetPublicIpAdressFromWLProxyClientIP() {
		mutableHttpServletRequest = new MutableHttpServletRequest(httpServletRequest);
		mutableHttpServletRequest.putHeader("x-forwarded-for", null);
		mutableHttpServletRequest.putHeader("Proxy-Client-IP", null);
		mutableHttpServletRequest.putHeader("WL-Proxy-Client-IP", "10.78.243.193");
		try {
			servlet.doGet(mutableHttpServletRequest, httpServletResponse);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ServletException e) {
			e.printStackTrace();
		}

		Assert.assertEquals("10.78.243.193", httpLogsFilter.getPublicIpAdress(mutableHttpServletRequest));
	}

	/**
	 * Test method for
	 * {@link com.github.marocraft.trackntrace.http.filter.HttpLogsFilter#getPublicIpAdress(javax.servlet.http.HttpServletRequest)}.
	 */
	@Test
	public void shouldGetPublicIpAdressFromRemote() {
		mutableHttpServletRequest = new MutableHttpServletRequest(httpServletRequest);
		mutableHttpServletRequest.putHeader("x-forwarded-for", null);
		mutableHttpServletRequest.putHeader("Proxy-Client-IP", null);
		mutableHttpServletRequest.putHeader("WL-Proxy-Client-IP", null);
		try {
			servlet.doGet(mutableHttpServletRequest, httpServletResponse);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ServletException e) {
			e.printStackTrace();
		}

		Assert.assertEquals(mutableHttpServletRequest.getRemoteAddr(),
				httpLogsFilter.getPublicIpAdress(mutableHttpServletRequest));
	}

	@Test
	public void testFilter() {
		HttpServletRequest mockReq = Mockito.mock(HttpServletRequest.class);
		HttpServletResponse mockResp = Mockito.mock(HttpServletResponse.class);
		FilterChain mockFilterChain = Mockito.mock(FilterChain.class);
		FilterConfig mockFilterConfig = Mockito.mock(FilterConfig.class);
		Mockito.when(mockReq.getRequestURI()).thenReturn("/");

		BufferedReader br = new BufferedReader(new StringReader("test"));
		try {
			Mockito.when(mockReq.getReader()).thenReturn(br);
			filter.init(mockFilterConfig);
			filter.doFilter(mockReq, mockResp, mockFilterChain);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ServletException e) {
			e.printStackTrace();
		}

		filter.destroy();
	}

}
