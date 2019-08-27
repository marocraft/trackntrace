/**
 * 
 */
package com.github.marocraft.trackntrace.http.filter;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;
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

	public void testRedirect() throws Exception {
		final MockHttpSession session = new MockHttpSession();
		final MockHttpServletRequest request = new MockHttpServletRequest();
		final MockHttpServletResponse response = new MockHttpServletResponse();
		request.setSession(session);
		this.filter.doFilter(request, response, null);
		assertEquals("" + "?service=" + URLEncoder.encode("", "UTF-8"), response.getRedirectedUrl());
		filter.destroy();
	}

}
