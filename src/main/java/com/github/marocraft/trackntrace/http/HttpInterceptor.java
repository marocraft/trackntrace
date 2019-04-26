package com.github.marocraft.trackntrace.http;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

/**
 * Intercept all http request to search for correlation-id
 * 
 * @author Khalid ELABBADI
 * @author Houseine TASSA
 *
 */
@Component
@WebServlet(urlPatterns = { "/*" }, name = "tnt-servlet")
public class HttpInterceptor extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) {
		// TODO: find correlation ids here and log them
		/**
		 * Hints: use CorrelationName constants for correlations variables names On
		 * spring boot main class use this annotations to enable this servlet and
		 * trackntrace: - @EnableTracknTrace - @ServletComponentScan(PackageName.HTTP)
		 */
	}
}