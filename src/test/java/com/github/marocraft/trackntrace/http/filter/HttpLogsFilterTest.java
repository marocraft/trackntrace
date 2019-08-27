/**
 * 
 */
package com.github.marocraft.trackntrace.http.filter;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * 
 *
 */
public class HttpLogsFilterTest {
	HttpServletRequest httpServletRequest;
	HttpServletResponse httpServletResponse;
	HttpLogsFilter httpLogsFilter;
	TestServlet servlet;
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

		Assert.assertEquals(mutableHttpServletRequest.getRemoteAddr(), httpLogsFilter.getPublicIpAdress(mutableHttpServletRequest));
	}	

}
