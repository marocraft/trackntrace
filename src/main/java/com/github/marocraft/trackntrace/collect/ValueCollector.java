package com.github.marocraft.trackntrace.collect;

import java.lang.reflect.Field;

import com.github.marocraft.trackntrace.annotation.Mapping;
import com.github.marocraft.trackntrace.domain.LogTrace;

/**
 * Find value of fieldname of a collection of fields from a LogTrace object
 * 
 * @author Housseine TASSA
 * @author Khalid ELABBADI
 */
public class ValueCollector {
	
	/**
	 * Just for hidding public constructor
	 */
	private ValueCollector() {

	}

	public static Object valueOf(String fieldName, LogTrace trace) throws IllegalAccessException {
		Field[] clazzFields = trace.getClass().getDeclaredFields();
		for (Field clazzField : clazzFields) {
			Mapping annotation = clazzField.getAnnotation(Mapping.class);
			if (annotation != null && annotation.field().equals(fieldName)) {
				clazzField.setAccessible(true);
				return clazzField.get(trace);
			}
		}

		return null;
	}
}