///**
// * 
// */
//package com.github.marocraft.trackntrace.http.filter;
//
//import javax.servlet.Filter;
//import javax.servlet.FilterConfig;
//import javax.servlet.ServletContext;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.http.HttpHeaders;
//import org.springframework.mock.web.MockFilterChain;
//import org.springframework.mock.web.MockFilterConfig;
//import org.springframework.mock.web.MockHttpServletRequest;
//import org.springframework.mock.web.MockServletContext;
//
///**
// * 
// *
// */
//public class CorrelationFilterTest {
//	private static final String FIRST_TASK_ATTRIBUTES = "{\"name\":\"First task\"}";
//
//	private static final String SOME_TASK_ATTRIBUTES = "{\"name\":\"Some task\"}";
//
//	private static final String FIRST_TASK_LINKS = "{\"self\":\"http://localhost:8080/api/tasks/1\"}";
//
//	private static final String PROJECT1_RELATIONSHIP_LINKS = "{\"self\":\"http://localhost:8080/api/tasks/1/relationships/project\","
//			+ "\"related\":\"http://localhost:8080/api/tasks/1/project\"}";
//
//	private static final String RESOURCE_SEARCH_PACKAGE = "io.crnk.servlet.resource";
//
//	private static final String RESOURCE_DEFAULT_DOMAIN = "http://localhost:8080";
//
//	private static Logger log = LoggerFactory.getLogger(CorrelationFilterTest.class);
//
//	private ServletContext servletContext;
//
//	private FilterConfig filterConfig;
//
//	private Filter filter;
//
//	@Before
//	public void before() throws Exception {
//		filter = new CorrelationFilter();
//
//		servletContext = new MockServletContext();
//		((MockServletContext) servletContext).setContextPath("");
//		filterConfig = new MockFilterConfig(servletContext);
////		((MockFilterConfig) filterConfig).addInitParameter(CrnkProperties.WEB_PATH_PREFIX, "/api");
////		((MockFilterConfig) filterConfig).addInitParameter(CrnkProperties.RESOURCE_SEARCH_PACKAGE,
////				RESOURCE_SEARCH_PACKAGE);
////		((MockFilterConfig) filterConfig).addInitParameter(CrnkProperties.RESOURCE_DEFAULT_DOMAIN,
////				RESOURCE_DEFAULT_DOMAIN);
//
//		filter.init(filterConfig);
//	}
//
//	// onSimpleCollectionGetShouldReturnCollectionOfResources
//	@Test
//	public void test() {
//		MockFilterChain filterChain = new MockFilterChain();
//		MockHttpServletRequest request = new MockHttpServletRequest(servletContext);
//		request.setMethod("GET");
//		request.setContextPath("");
//		request.setServletPath(null);
//		request.setPathInfo(null);
//		request.setRequestURI("/api/tasks/");
//		request.setContentType("application/json");
//		request.addHeader("Accept", "*/*");
//
//	}
//
//}
