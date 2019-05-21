package com.github.marocraft.trackntrace.utils;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.github.marocraft.trackntrace.context.SpringBasicContext;
import com.github.marocraft.trackntrace.domain.DefaultLogTrace;
import com.github.marocraft.trackntrace.utils.CommonUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringBasicContext.class)
public class CommonUtilsTest {

	@Autowired
	CommonUtils commonUtils;

	@Test
	public void shouldGetValueForObject() throws IllegalAccessException {
		DefaultLogTrace trace = new DefaultLogTrace();
		trace.setMethod("Method");
		Object value = commonUtils.valueOf("methodName", trace);
		Assert.assertNotNull(value);
	}
}