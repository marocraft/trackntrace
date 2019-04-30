//package com.github.marocraft.trackntrace.http;
//
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import com.github.marocraft.trackntrace.enums.CorrelationName;
//import com.github.marocraft.trackntrace.utils.CommonUtils;
//
///**
// * Intercept all http request to search for correlation-id
// * 
// * @author Khalid ELABBADI
// * @author Houseine TASSA
// *
// */
////@Component
////@WebServlet(urlPatterns = { "/*" }, name = "tnt-servlet")
//public class HttpInterceptor extends HttpServlet {
//
//	private static final long serialVersionUID = 1L;
//	@Autowired
//	Correlater httpRequestStatus;
//	
//	@Autowired
//	CommonUtils commonUtils;
//
//	@Override
//	protected void service(HttpServletRequest request, HttpServletResponse response) {
//		if (request.getHeader(CorrelationName.TRACE_ID) == null) {
//			// create tracer id
//			httpRequestStatus.setTraceId(commonUtils.uniqid());
//			response.setHeader(CorrelationName.TRACE_ID, httpRequestStatus.getTraceId());
//		}else {
//			httpRequestStatus.setTraceId(request.getHeader(CorrelationName.TRACE_ID));
//		}
//		
//		if (request.getHeader(CorrelationName.SPAN_ID) == null) {
//			// create span id
//			httpRequestStatus.setSpanId((commonUtils.uniqid()));
//			response.setHeader(CorrelationName.SPAN_ID, httpRequestStatus.getSpanId());
//		}else {
//			httpRequestStatus.setSpanId(request.getHeader(CorrelationName.SPAN_ID));
//		}
//		
//	}
//}