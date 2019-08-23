package com.github.marocraft.trackntrace.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.lang.reflect.Method;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.github.marocraft.trackntrace.context.SpringBasicContext;
import com.github.marocraft.trackntrace.domain.DefaultLogTrace;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringBasicContext.class)
public class CommonUtilsTest {

	@Autowired
	CommonUtils commonUtils;

	String format;

	@Before
	public void before() {
		format = "{\"methodName\": \"{{methodName}}\",\"className\": \"{{className}}\",\"logLevel\": \"{{logLevel}}\",\"executionTime\": \"{{executionTime}}\",\"logMessage\": \"{{logMessage}}\",\"timeStamps\": \"{{timeStamps}}\",\"traceId\": \"{{traceId}}\",\"spanId\": \"{{spanId}}\",\"parentId\": \"{{parentId}}\",\"ip\": \"{{ip}}\"}";

	}

	@Test
	public void shouldRplaceFildsInFormatWithValues() {
		try {
			Class<?> className=CommonUtilsTest.class;
		    Method[] methods = className.getMethods();
		    DefaultLogTrace trace = new DefaultLogTrace();
		    for (Method method : methods) {
				if((method.toString()).contains("testMethod")) {
					trace.setClazz(className.getSimpleName());
					trace.setMethod(method.toString());
				}
			}
			String log = commonUtils.replace(format, "methodName", trace);
			assertNotNull(log);
			assertEquals("{\"methodName\": \"public void com.github.marocraft.trackntrace.utils.CommonUtilsTest.testMethod()\",\"className\": \"{{className}}\",\"logLevel\": \"{{logLevel}}\",\"executionTime\": \"{{executionTime}}\",\"logMessage\": \"{{logMessage}}\",\"timeStamps\": \"{{timeStamps}}\",\"traceId\": \"{{traceId}}\",\"spanId\": \"{{spanId}}\",\"parentId\": \"{{parentId}}\",\"ip\": \"{{ip}}\"}", log);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
	@Test
	public void shouldReturnRandomLong() {
		assertNotNull(CommonUtils.nextId());
	}
	
	@Test
	public void shouldConvertLongToLowerHex() {
		assertNotNull(CommonUtils.toLowerHex(CommonUtils.nextId()));
	}
	
	public void testMethod() {
		
	}
}