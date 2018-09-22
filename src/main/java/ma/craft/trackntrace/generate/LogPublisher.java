package ma.craft.trackntrace.generate;

import java.util.ArrayList;
import java.util.List;

public class LogPublisher {

    private static final LogPublisher INSTANCE = new LogPublisher();
    private List<String> logs = new ArrayList<>();

    private LogPublisher(){
    }

    public static LogPublisher instance(){
        return INSTANCE;
    }

    public void publish(String logMessage) {
        logs.add(logMessage);
    }

    public void clear(){
        logs.clear();
    }

    public Boolean empty(){
       return logs.isEmpty();
    }

    public Integer logStackSize(){
        return logs.size();
    }
}
