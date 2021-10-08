package domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class WorkStartTime {
    @Getter
    private final Integer startTime;
    @Getter
    private final Integer startH;
    @Getter
    private final Integer startM;

}
