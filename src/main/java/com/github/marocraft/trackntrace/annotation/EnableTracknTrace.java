package com.github.marocraft.trackntrace.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.ComponentScan;

/**
 * Enables TacknTrace on the target project. This annotation must be used on the
 * project main class
 * 
 * @author Khalid ELABBADI
 * @since 0.0.4
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@ComponentScan("com.github.marocraft.trackntrace.*")
public @interface EnableTracknTrace {

}