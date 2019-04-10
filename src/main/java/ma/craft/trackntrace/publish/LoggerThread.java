package ma.craft.trackntrace.publish;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component("loggerThread")
public class LoggerThread extends Thread {
	
	final Logger LOG = LoggerFactory.getLogger(LoggerThread.class);
	
	@Override
	public void run() {
		String currentMessage = "";
		while (true) {
			try {
				currentMessage = LogPublisher.LOG_QUEUE.take();
				LOG.info(currentMessage);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
}