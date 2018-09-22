package ma.craft.trackntrace;


import ma.craft.trackntrace.context.SpringAOPContext;
import ma.craft.trackntrace.generate.LogPublisher;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringAOPContext.class)
public class AspectAnnotationTest {

    @Autowired
    TestService testService;

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


}
