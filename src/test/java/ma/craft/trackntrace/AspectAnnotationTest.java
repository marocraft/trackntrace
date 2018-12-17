package ma.craft.trackntrace;

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
import ma.craft.trackntrace.generate.TemplateReader;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringAOPContext.class)
public class AspectAnnotationTest {

	@Autowired
	TestService testService;
	@Autowired
	Template template;

	@Test
	public void shouldLogHaveInfoLevel() {
		Assert.assertTrue(LogPublisher.instance().empty());
		testService.sleep(300);
		Assert.assertFalse(LogPublisher.instance().empty());
		Assert.assertEquals((Integer) 1, LogPublisher.instance().logStackSize());
	}

	@Test
	public void shouldClearLogGeneratorStack() {
		testService.sleep(300);
		LogPublisher.instance().clear();
		Assert.assertEquals((Integer) 0, LogPublisher.instance().logStackSize());
	}

	@Test
	public void shouldCreateLogsfile() throws IOException {
		testService.sleep(200);
		template = TemplateReader.readTemplate();
		LogPublisher.instance().exportFile(template.getLogsPath(), LogPublisher.instance().getLogs());

	}

}
