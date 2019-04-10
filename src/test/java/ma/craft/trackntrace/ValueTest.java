package ma.craft.trackntrace;

import ma.craft.trackntrace.collect.ValueCollector;
import ma.craft.trackntrace.domain.LogTrace;
import org.junit.Assert;
import org.junit.Test;

public class ValueTest {


    @Test
    public void shouldGetValueForObject() throws IllegalAccessException {
        LogTrace trace = new LogTrace();
        trace.setMethod("Method");
        Object value = ValueCollector.valueOf("methodName", trace);
        Assert.assertNotNull(value);
    }
}
