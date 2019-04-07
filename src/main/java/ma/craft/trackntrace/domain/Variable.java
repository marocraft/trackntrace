package ma.craft.trackntrace.domain;

import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Variable {
    private String name;
    private int start;
    private int end;
}
