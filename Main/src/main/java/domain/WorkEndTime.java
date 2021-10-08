package domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class WorkEndTime {
    @Getter
    private final Integer endTime;
    @Getter
    private final Integer endH;
    @Getter
    private final Integer endM;

}