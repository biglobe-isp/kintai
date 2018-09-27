package com.naosim.dddwork.domain.attendance;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * 労働時間
 */
@ToString(includeFieldNames = false)
@EqualsAndHashCode
@AllArgsConstructor
public class WorkMinutes {
    @Getter
    private final int value;

    public WorkMinutes(StartTime startTime, EndTime endTime) {

        this.value = calcWorkMinutes(
                startTime.makeStartTimeHours(),
                startTime.makeStartTimeMinutes(),
                endTime.makeEndTimeHours(),
                endTime.makeEndTimeMinutes());
    }

    private int calcWorkMinutes(StartTimeHours startTimeHours,
                                StartTimeMinutes startTimeMinutes,
                                EndTimeHours endTimeHours,
                                EndTimeMinutes endTimeMinutes) {

        int workMinutes =
                endTimeHours.getValue() * 60 + endTimeMinutes.getValue()
                        - (startTimeHours.getValue() * 60 + startTimeMinutes.getValue());

        if (endTimeHours.isOver12Hours()) {
            workMinutes -= 60;
        }

        if (endTimeHours.isOver18Hours()) {
            workMinutes -= 60;
        }

        if (endTimeHours.isOver21Hours()) {
            workMinutes -= 60;
        }

        if (endTimeHours.isBreak()) {
            workMinutes -= endTimeMinutes.getValue();
        }

        return workMinutes;
    }
}
