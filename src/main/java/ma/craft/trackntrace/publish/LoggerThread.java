package ma.craft.trackntrace.publish;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component("loggerThread")
public class LoggerThread extends Thread {

	final Logger LOG = LoggerFactory.getLogger(LoggerThread.class);

	@Override
	public void run() {
		while (true) {
			try {
				LOG.info(LogPublisher.LOG_QUEUE.take());
				} catch (Exception ex) {
					ex.printStackTrace();
				}
		}
	}
}