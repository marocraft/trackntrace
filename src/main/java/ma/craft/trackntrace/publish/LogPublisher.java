package ma.craft.trackntrace.publish;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * Permet de publier les logs collectés dans une Qeue bloquante
 * 
 *@author Housseine Tassa
 */
@Getter
@Component
@Scope("singleton")
@Slf4j
public class LogPublisher implements ILogPublisher<String> {

	private BlockingQueue<String> LOG_QUEUE = new LinkedBlockingDeque<>(50);

	@Override
	public void publish(String message) {
		try {
			LOG_QUEUE.put(message);
		} catch (Exception ex) {
			log.error(message, ex);
		}
	}

	@Override
	public String get() throws InterruptedException {
		return LOG_QUEUE.take();
	}

	public void clear() {
		LOG_QUEUE.clear();
	}

	public int size() {
		return LOG_QUEUE.size();
	}
	
	
}