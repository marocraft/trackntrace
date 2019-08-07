package com.github.marocraft.trackntrace.collect;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.lang.reflect.Method;

import org.aspectj.lang.reflect.MethodSignature;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.github.marocraft.trackntrace.annotation.Trace;
import com.github.marocraft.trackntrace.domain.LogLevel;

/**
 * 
 *
 */
public class ILogCollectorTest {

	ILogCollector iLogCollector = new DefaultLogCollector();;

	MethodSignature signature;

	@Before
	public void beforeTests() {
		signature = mock(MethodSignature.class);
		when(signature.getMethod()).thenReturn(myMethod());

	}

	/**
	 * Test method for
	 * {@link com.github.marocraft.trackntrace.collect.ILogCollector#getLevelFromSignature(org.aspectj.lang.reflect.MethodSignature)}.
	 */
	@Test
	public void shouldGetLevelFromSignature() {
		Assert.assertEquals("TRACE", iLogCollector.getLevelFromSignature(signature).toString());
	}

	/**
	 * Test method for
	 * {@link com.github.marocraft.trackntrace.collect.ILogCollector#getMessageFromSignature(org.aspectj.lang.reflect.MethodSignature)}.
	 */
	@Test
	public void shouldGetMessageFromSignature() {

		assertEquals("mocked method test", iLogCollector.getMessageFromSignature(signature));
	}

	public Method myMethod() {
		try {
			return getClass().getDeclaredMethod("mockedMethod");
		} catch (NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Trace(level = LogLevel.TRACE, message = "mocked method test")
	public void mockedMethod() {

	}

}
