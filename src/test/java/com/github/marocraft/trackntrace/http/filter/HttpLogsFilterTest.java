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
	
	@Before
	public void before() {
	httpServletRequest=Mockito.mock(HttpServletRequest.class);
	httpServletResponse=Mockito.mock(HttpServletResponse.class);
	httpLogsFilter= new HttpLogsFilter();
	servlet= new TestServlet();
	
	}
	

	/**
	 * Test method for {@link com.github.marocraft.trackntrace.http.filter.HttpLogsFilter#getPublicIpAdress(javax.servlet.http.HttpServletRequest)}.
	 */
	@Test
	public void shouldGetPublicIpAdress() {
		MutableHttpServletRequest mutableHttpServletRequest=new MutableHttpServletRequest(httpServletRequest);
		mutableHttpServletRequest.putHeader("x-forwarded-for", "10.78.243.194");
		try {
			servlet.doGet(mutableHttpServletRequest, httpServletResponse);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ServletException e) {
			e.printStackTrace();
		}
		System.out.println(mutableHttpServletRequest.getHeader("x-forwarded-for"));
		Assert.assertEquals("10.78.243.194", httpLogsFilter.getPublicIpAdress(mutableHttpServletRequest));
		
	}

}
