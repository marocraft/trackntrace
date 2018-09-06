package ma.craft.trackntrace;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import ma.craft.trackntrace.TrackntraceApplication;
import ma.craft.trackntrace.service.TestController;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TrackntraceApplicationTests {

	@Autowired
	TestController controller;
	
	@Test
	public void contextLoads() {
		assertThat(controller).isNotNull();
		//assertEquals(10, controller.multiplication(5, 2));
	}

}
