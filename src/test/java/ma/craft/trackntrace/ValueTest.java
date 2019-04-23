package ma.craft.trackntrace;

import org.junit.Assert;
import org.junit.Test;

import com.github.marocraft.trackntrace.collect.ValueCollector;
import com.github.marocraft.trackntrace.domain.LogTrace;

public class ValueTest {

	@Test
	public void shouldGetValueForObject() throws IllegalAccessException {
		LogTrace trace = new LogTrace();
		trace.setMethod("Method");
		Object value = ValueCollector.valueOf("methodName", trace);
		Assert.assertNotNull(value);
	}
}