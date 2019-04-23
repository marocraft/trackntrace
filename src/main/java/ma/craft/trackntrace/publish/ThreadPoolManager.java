package ma.craft.trackntrace.publish;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:/application.yml")
public class ThreadPoolManager {

	@Value("${tnt.threadpool:1}")
	Integer threadpoolsize;

	ExecutorService executorService;

	public void initialize() {
		executorService = Executors.newFixedThreadPool(threadpoolsize);
	}

	public void submitThread(Runnable thread) {
		executorService.submit(thread);
	
	}

	public void shutDownThreadPool() {
		executorService.shutdown();
	}
}