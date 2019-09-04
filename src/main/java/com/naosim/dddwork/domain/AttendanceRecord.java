package com.naosim.dddwork.domain;

import com.naosim.dddwork.domain.date.WorkingDate;
import com.naosim.dddwork.domain.time.WorkingDuration;
import lombok.Getter;

public class AttendanceRecord {
    @Getter
    private final WorkingDate workingDate;
    @Getter
    private final WorkingDuration workingDuration;

    public AttendanceRecord(WorkingDate workingDate, WorkingDuration workingDuration) {
        this.workingDate = workingDate;
        this.workingDuration = workingDuration;
    }

    public String toString() {
        return String.format(
                "%04d%02d%02d %02d:%02d-%02d:%02d",
                workingDate.getYear().getYear(),
                workingDate.getMonth().getMonth(),
                workingDate.getDay().getDay(),
                workingDuration.getStartTime().getHour().getHour(),
                workingDuration.getStartTime().getMinute().getMinute(),
                workingDuration.getEndTime().getHour().getHour(),
                workingDuration.getEndTime().getMinute().getMinute()
        );
    }
}
