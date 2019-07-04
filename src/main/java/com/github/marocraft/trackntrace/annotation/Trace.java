package com.github.marocraft.trackntrace.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.web.bind.annotation.Mapping;

import com.github.marocraft.trackntrace.domain.LogLevel;

/**
 * Enables automatically technical auto-logging for the target. For example: if
 * target is method m of class c, this will log automatically technical
 * information for every single access to this method.
 * 
 * @author Houseine TASSA
 * @author Sallah KOKAINA
 * @since 0.0.3
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Trace {

	/**
	 * Add a message to log as string
	 * 
	 * @return
	 */
	public String message() default "";

	/**
	 * Specify a logging level for logging as
	 * {@link com.github.marocraft.trackntrace.domain.LogLevel}
	 * 
	 * @return
	 */
	public LogLevel level() default LogLevel.TRIVIAL;
}