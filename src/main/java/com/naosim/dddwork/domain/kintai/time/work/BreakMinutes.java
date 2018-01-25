package com.naosim.dddwork.domain.kintai.time.work;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
public class BreakMinutes {

    @Getter
    private final WorkStartTime workStartTime;

    @Getter
    private final WorkEndTime workEndTime;

    public int getLunchBreakMinutes() {
        return this.getBreakMinutes(12);
    }

    public int getEveningBreakMinutes() {
        return this.getBreakMinutes(18);
    }

    public int getNightBreakMinutes() {
        return this.getBreakMinutes(21);
    }

    private int getBreakMinutes(int breakStartHour) {
        int workStartHour = this.workStartTime.getTime().getHour().getValue();
        int workStartMinute = this.workStartTime.getTime().getMinute().getValue();
        int workEndHour = this.workEndTime.getTime().getHour().getValue();
        int workEndMinute = this.workEndTime.getTime().getMinute().getValue();

        if (workEndHour == breakStartHour) {
            if (workStartHour < breakStartHour) return workEndMinute;
            if (workStartHour == breakStartHour)
                return workEndMinute - workStartMinute;
        } else if (workEndHour > breakStartHour) {
            return 60;
        }
        return 0;
    }
}
