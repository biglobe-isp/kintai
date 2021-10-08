package domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class OverWorkTime {
    @Getter
    private Integer value;

    public OverWorkTime calculate(WorkStartTime workStartTime, WorkEndTime workEndTime) {
        Integer startH = workStartTime.getStartH();
        Integer startM = workStartTime.getStartM();
        Integer endH = workEndTime.getEndH();
        Integer endM = workEndTime.getEndM();
        Integer workMinutes = endH * 60 + endM - (startH * 60 + startM);

        Integer overWorkMinutes = Math.max(workMinutes - 8 * 60, 0);
        value = overWorkMinutes;
        return new OverWorkTime(value);
    }
}