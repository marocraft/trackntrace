package com.github.marocraft.trackntrace.annotation;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.github.marocraft.trackntrace.TracknTraceComponentsActivator;

/**
 * Enables TacknTrace on the target project. This annotation must be used on the
 * project main class
 * 
 * @author Khalid ELABBADI
 * @since 0.0.3
 */
@Retention(value = RUNTIME)
@Target(value = { TYPE })
@Configuration
@ComponentScan(basePackageClasses = TracknTraceComponentsActivator.class)
@ComponentScan(basePackages = "com.github.marocraft.trackntrace")
public @interface EnableTracknTrace {

}