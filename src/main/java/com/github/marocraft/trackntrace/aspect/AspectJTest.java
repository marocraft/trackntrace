package com.github.marocraft.trackntrace.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.github.marocraft.trackntrace.annotation.Trace;

@Aspect
@Component
public class AspectJTest {
	@Pointcut("@annotation(testModeOnly)")
	public void testModeOnlyMethods(Trace testModeOnly) {
	}

	@Around("testModeOnlyMethods(testModeOnly)")
	public Object testModeOnly(ProceedingJoinPoint joinPoint,Trace testModeOnly) throws Throwable {
		System.out.println("class name: "+joinPoint.getClass().getName());
		System.out.println("methodeName: "+joinPoint.getSignature().getName());
		return joinPoint.proceed();
	}
}
