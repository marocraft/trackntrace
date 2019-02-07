package ma.craft.trackntrace;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ma.craft.trackntrace.context.SpringAOPContext;
import ma.craft.trackntrace.domain.Template;
import ma.craft.trackntrace.generate.LogPublisher;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringAOPContext.class)
public class AspectAnnotationTest {

	@Autowired
	TestService testService;
	@Autowired
	Template template;

	@Test
	public void shouldLogHaveInfoLevel() throws InterruptedException, IOException, FileNotFoundException {
		Assert.assertTrue(LogPublisher.instance().empty());
		testService.sleep(300);
		Assert.assertFalse(LogPublisher.instance().empty());
		Assert.assertEquals((Integer) 1, LogPublisher.instance().logStackSize());
	}

	@Test
	public void shouldClearLogGeneratorStack() throws InterruptedException, FileNotFoundException {
		testService.sleep(300);
		LogPublisher.instance().clear();
		Assert.assertEquals((Integer) 0, LogPublisher.instance().logStackSize());
	}

	@Test
	public void shouldCreateLogsfile() throws IOException, InterruptedException, FileNotFoundException {
		testService.sleep(200);
		LogPublisher.instance().exportFile(template.getLogsPath(), LogPublisher.instance().getLogs());

	}

	@Test
	public void shouldCreateLogsfileWithExportMdc() throws IOException, InterruptedException, FileNotFoundException {
		testService.sleep(200);
		LogPublisher.instance().exportmdc(LogPublisher.instance().getLogs());

	}

}
