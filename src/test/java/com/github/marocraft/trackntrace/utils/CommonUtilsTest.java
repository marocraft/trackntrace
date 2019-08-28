package com.github.marocraft.trackntrace.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

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
	DefaultLogTrace trace;

	@Before
	public void before() {
		format = "{\"methodName\": \"{{methodName}}\",\"className\": \"{{className}}\",\"logLevel\": \"{{logLevel}}\",\"executionTime\": \"{{executionTime}}\",\"logMessage\": \"{{logMessage}}\",\"timeStamps\": \"{{timeStamps}}\",\"traceId\": \"{{traceId}}\",\"spanId\": \"{{spanId}}\",\"parentId\": \"{{parentId}}\",\"ip\": \"{{ip}}\"}";

		Class<?> className = CommonUtilsTest.class;
		Method[] methods = className.getMethods();
		trace = new DefaultLogTrace();
		for (Method method : methods) {
			if ((method.toString()).contains("testMethod")) {
				trace.setClazz(className.getSimpleName());
				trace.setMethod(method.toString());
			}
		}
	}

	@Test
	public void shouldRplaceFildsInFormatWithValues() {
		try {

			String log = commonUtils.replace(format, "methodName", trace);
			assertNotNull(log);
			assertEquals(
					"{\"methodName\": \"public void com.github.marocraft.trackntrace.utils.CommonUtilsTest.testMethod()\",\"className\": \"{{className}}\",\"logLevel\": \"{{logLevel}}\",\"executionTime\": \"{{executionTime}}\",\"logMessage\": \"{{logMessage}}\",\"timeStamps\": \"{{timeStamps}}\",\"traceId\": \"{{traceId}}\",\"spanId\": \"{{spanId}}\",\"parentId\": \"{{parentId}}\",\"ip\": \"{{ip}}\"}",
					log);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}

	}

	@Test
	public void replaceShouldReturnNull() {

		String log;
		try {
			log = commonUtils.replace(format, "methodName", null);
			assertNotNull(log);
			assertEquals(
					"{\"methodName\": \"null\",\"className\": \"{{className}}\",\"logLevel\": \"{{logLevel}}\",\"executionTime\": \"{{executionTime}}\",\"logMessage\": \"{{logMessage}}\",\"timeStamps\": \"{{timeStamps}}\",\"traceId\": \"{{traceId}}\",\"spanId\": \"{{spanId}}\",\"parentId\": \"{{parentId}}\",\"ip\": \"{{ip}}\"}",
					log);

			log = commonUtils.replace(format, null, null);
			assertEquals(
					"{\"methodName\": \"{{methodName}}\",\"className\": \"{{className}}\",\"logLevel\": \"{{logLevel}}\",\"executionTime\": \"{{executionTime}}\",\"logMessage\": \"{{logMessage}}\",\"timeStamps\": \"{{timeStamps}}\",\"traceId\": \"{{traceId}}\",\"spanId\": \"{{spanId}}\",\"parentId\": \"{{parentId}}\",\"ip\": \"{{ip}}\"}",
					log);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	
	public void replaceShouldThowException() throws Exception {
		String log = commonUtils.replace(null, null, null);
		assertNull(log);
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