package com.example.demo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.example.demo.entities.AnnotationAspect;
import com.example.demo.entities.LogBuilder;


public class AnnotationAspectTest {

	@Test
	public void test() {
		AnnotationAspect exempleAspect = new AnnotationAspect();
		assertNotNull(exempleAspect);
		//assertEquals("classe : com.example.demo.controllers.Controller, name : tes,arguments : (), execution time: 0 ms",exempleAspect);
	}
	
	@Test
	public void shouldLogHaveCorrectFormat() {
		String log = LogBuilder.build("Controller", "myMethod", new Object[] {}, 20L);
		assertEquals("", log);
	}

}
