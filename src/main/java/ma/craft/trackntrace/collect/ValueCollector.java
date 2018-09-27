package ma.craft.trackntrace.collect;

import ma.craft.trackntrace.annotation.Mapping;
import ma.craft.trackntrace.domain.LogTrace;

import java.lang.reflect.Field;

public class ValueCollector {

    public static Object valueOf(String fieldName, LogTrace trace) throws IllegalAccessException {
        Field[] clazzFields = trace.getClass().getDeclaredFields();
        for (Field clazzField: clazzFields) {
            Mapping annotation = clazzField.getAnnotation(Mapping.class);
            if (annotation != null && annotation.field().equals(fieldName)){
                clazzField.setAccessible(true);
                return clazzField.get(trace);
            }
        }
        return null;
    }
}
