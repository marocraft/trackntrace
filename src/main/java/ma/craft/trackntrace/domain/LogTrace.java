package ma.craft.trackntrace.domain;

        import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LogTrace {
    private long executionTime;
    private String methodName;
    private String className;
    private String logLevel;
}
