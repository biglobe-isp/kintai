package com.naosim.dddwork.domain;

import io.vavr.collection.List;
import lombok.Getter;

import java.time.Duration;

public class AttendanceSummary {
    @Getter
    private RegularWorkingDuration regularWorkingDuration;
    @Getter
    private OverTimeWorkingDuration overTimeWorkingDuration;

    public AttendanceSummary(AttendanceRecords attendanceRecords) {

        List<WorkingDurationExceptBreak> list = attendanceRecords.getAttendanceRecords()
                .map(r -> new WorkingDurationExceptBreak(r));

        long regularTimeMinutes = list.map(r -> new RegularWorkingDuration(r))
                .map(r -> r.getRegularWorkingDuration().toMinutes())
                .sum()
                .longValue();
        long overTimeMintes = list.map(r -> new OverTimeWorkingDuration(r))
                .map(r -> r.getOverTimeWorkingDuration().toMinutes())
                .sum()
                .longValue();


        regularWorkingDuration = new RegularWorkingDuration(Duration.ofMinutes(regularTimeMinutes));
        overTimeWorkingDuration = new OverTimeWorkingDuration(Duration.ofMinutes(overTimeMintes));
    }

    public long getRegularWorkingHours() {
        return regularWorkingDuration.getRegularWorkingDuration().toHours();
    }

    public long getRegularWorkingMinutes() {
        return regularWorkingDuration.getRegularWorkingDuration().toMinutes() % 60;
    }

    public long getOverTimeWorkingHours() {
        return overTimeWorkingDuration.getOverTimeWorkingDuration().toHours();
    }

    public long getOverTimWorkingMinutes() {
        return overTimeWorkingDuration.getOverTimeWorkingDuration().toMinutes() % 60;
    }

    public String toString() {
        return regularWorkingDuration.getRegularWorkingDuration()
                .toHours() + ":" + regularWorkingDuration.getRegularWorkingDuration()
                .toMinutes() % 60 + "/" + overTimeWorkingDuration.getOverTimeWorkingDuration()
                .toHours() + ":" + overTimeWorkingDuration.getOverTimeWorkingDuration().toMinutes() % 60;
    }
}
