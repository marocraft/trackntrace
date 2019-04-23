package ma.craft.trackntrace.publish;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * Permet de publier les logs collectés dans une Qeue bloquante
 * 
 * @author Housseine Tassa
 */
@Getter
@Component("logPublisher")
@Slf4j
public class LogPublisher implements ILogPublisher<String> {

	private BlockingQueue<String> logQeue = new LinkedBlockingDeque<>(50);

	@Override
	public void publish(String message) {
		try {
			logQeue.put(message);
		} catch (Exception ex) {
			log.error(message, ex);
		}
	}

	@Override
	public String get() throws InterruptedException {
		return logQeue.take();
	}

	public void clear() {
		logQeue.clear();
	}

	public int size() {
		return logQeue.size();
	}
}