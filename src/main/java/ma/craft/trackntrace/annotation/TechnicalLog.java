package ma.craft.trackntrace.annotation;

import ma.craft.trackntrace.domain.LogLevel;

import ma.craft.trackntrace.annotation.Trace;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Trace()
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface TechnicalLog {
	public String message() default "";
	public String code() default "" ;
	public LogLevel level() default LogLevel.NORMAL;
}
