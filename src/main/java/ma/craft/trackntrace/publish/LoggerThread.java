package ma.craft.trackntrace.publish;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * Permet de créer et d'éxecuter un Thread
 * 
 * @author Tassa Housseine
 */
@Slf4j
@Component
public class LoggerThread implements Runnable {

	@Autowired
	ILogPublisher<String> publisher;

	@Override
	public void run() {
		String msg = null;
		try {
			if (publisher != null) {
				while ((msg = publisher.get()) != null) {
					log.info(msg);
					Thread.sleep(10);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(), e);
		}
	}
}