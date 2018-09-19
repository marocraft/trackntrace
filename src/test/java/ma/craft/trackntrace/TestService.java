package ma.craft.trackntrace;

import ma.craft.trackntrace.annotation.BusinessLog;
import ma.craft.trackntrace.domain.LogLevel;
import org.springframework.stereotype.Component;

@Component
public class TestService {

    @BusinessLog(level = LogLevel.NORMAL)
    public void sleep(long sleep){
        try {
            Thread.sleep(sleep);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
