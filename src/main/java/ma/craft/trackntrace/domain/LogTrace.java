package ma.craft.trackntrace.domain;

import lombok.*;
import ma.craft.trackntrace.annotation.Mapping;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LogTrace {
    @Mapping(field = "executionTime")
    private long time;
    @Mapping(field = "methodName")
    private String method;
    @Mapping(field = "className")
    private String clazz;
    @Mapping(field = "logLevel")
    private String level;
    @Mapping(field = "codeName")
    private String code;

}
