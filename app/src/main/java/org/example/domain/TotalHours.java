package org.example.domain;

import lombok.Value;
import org.example.utils.WorkRole;

import java.time.LocalTime;

@Value
public class TotalHours {
    TotalWorkingHours totalWorkingHours;
    TotalOverTimeHours totalOverTimeHours;

    public static TotalHours of(WorkTime workTime) {
        LocalTime workStartTime = workTime.getWorkStartTime().getValue();
        LocalTime workEndTime = workTime.getWorkEndTime().getValue();
        int totalMinutes = (workEndTime.getHour() - workStartTime.getHour()) * 60
                + (workEndTime.getMinute() - workStartTime.getMinute());

        int breakTime = calculateBreakTime(workStartTime, workEndTime);

        int workMinutes = totalMinutes - breakTime;

        int legalWorkMinutes = Math.min(workMinutes, WorkRole.LEGAL_WORK_MINUTES);
        int overtimeMinutes = Math.max(0, workMinutes - WorkRole.LEGAL_WORK_MINUTES);

        return new TotalHours(
                new TotalWorkingHours(legalWorkMinutes),
                new TotalOverTimeHours(overtimeMinutes)
        );
    }

    private static int calculateBreakTime(LocalTime workStartTime, LocalTime workEndTime) {
        return BreakTimes.getBreakTimes()
                .stream()
                .mapToInt(breakTime -> breakTime.getBreakMinute(workStartTime, workEndTime)).sum();
    }
}
