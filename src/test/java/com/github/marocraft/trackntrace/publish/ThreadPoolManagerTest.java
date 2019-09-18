/**
 * 
 */
package com.github.marocraft.trackntrace.publish;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.util.concurrent.TimeUnit;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.github.marocraft.trackntrace.context.SpringBasicContext;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringBasicContext.class)
public class ThreadPoolManagerTest {

	@Autowired
	ThreadPoolManager threadPoolManager;

	/**
	 * Test method for
	 * {@link com.github.marocraft.trackntrace.publish.ThreadPoolManager#shutdown()}.
	 */
	@Test
	public void shouldShutdownThreadPool() {

		Runnable task1 = () -> {
			try {
				TimeUnit.SECONDS.sleep(2);
			} catch (InterruptedException ex) {
				throw new IllegalStateException(ex);
			}
		};
		threadPoolManager.initialize();
		threadPoolManager.addNewThread(task1);
		assertFalse(threadPoolManager.getExecutorService().isShutdown());

		threadPoolManager.shutdown();
		assertTrue(threadPoolManager.getExecutorService().isShutdown());

	}

}
