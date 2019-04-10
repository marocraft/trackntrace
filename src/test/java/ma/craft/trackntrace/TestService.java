package ma.craft.trackntrace;

import org.springframework.stereotype.Component;

import ma.craft.trackntrace.annotation.Trace;
import ma.craft.trackntrace.domain.LogLevel;

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
