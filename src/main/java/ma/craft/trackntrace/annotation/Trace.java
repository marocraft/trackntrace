package ma.craft.trackntrace.annotation;

import ma.craft.trackntrace.domain.LogLevel;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Trace {
	public String message() default "";
	public String code() default "";
	public LogLevel level() default LogLevel.TRIVIAL;
}
