package com.github.marocraft.trackntrace.annotation;

import org.springframework.stereotype.Component;

import com.github.marocraft.trackntrace.annotation.Trace;
import com.github.marocraft.trackntrace.domain.LogLevel;

@Component
public class TestService {

	@Trace(level = LogLevel.NORMAL, message = "new message")
	public void sleep(long sleep) {
		try {
			Thread.sleep(sleep);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}