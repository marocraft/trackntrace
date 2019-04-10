package ma.craft.trackntrace.publish;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import lombok.Getter;

@Getter
@Component
@Scope("singleton")
public class LogPublisher implements ILogPublisher {

	public static BlockingQueue<String> LOG_QUEUE = new LinkedBlockingDeque<>();
	private String var;

	@Override
	public void publish(String message) {
		try {
			LOG_QUEUE.put(message);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}