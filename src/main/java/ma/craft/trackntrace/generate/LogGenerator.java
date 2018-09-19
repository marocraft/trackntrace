package ma.craft.trackntrace.generate;

import java.util.ArrayList;
import java.util.List;

public class LogGenerator {

    private static final LogGenerator INSTANCE = new LogGenerator();
    private List<String> logs = new ArrayList<>();

    private LogGenerator(){
    }

    public static LogGenerator instance(){
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
