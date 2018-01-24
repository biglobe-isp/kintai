package com.naosim.dddwork.domain.time.work;

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
        if (this.workEndTime.getTime().getHour().getValue() == breakStartHour) {
            if (this.workStartTime.getTime().getHour().getValue() < breakStartHour) return this.workEndTime.getTime().getMinute().getValue();
            if (this.workStartTime.getTime().getHour().getValue() == breakStartHour)
                return this.workEndTime.getTime().getMinute().getValue() - this.workStartTime.getTime().getMinute().getValue();
        } else if (this.workEndTime.getTime().getHour().getValue() > breakStartHour) {
            return 60;
        }
        return 0;
    }
}
