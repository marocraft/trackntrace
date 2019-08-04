package com.github.marocraft.trackntrace.annotation;

import org.springframework.stereotype.Component;

import com.github.marocraft.trackntrace.annotation.Trace;
import com.github.marocraft.trackntrace.domain.LogLevel;

@Component
public class TestService {
	@Trace(level = LogLevel.INFO, message = "new message")
	public void sleep(long sleep) {
		try {
			Thread.sleep(sleep);
			System.out.println(affciher("here"));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Trace(level = LogLevel.INFO, message = "new message")
	public String affciher(String str) {
		return "it works " + str;
	}
}