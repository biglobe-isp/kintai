package org.example.domain;

import lombok.Value;

@Value
public class WorkTime {
    TotalWorkingHours totalWorkingHours;
    TotalOverTimeHours totalOverTimeHours;
    WorkStartTime workStartTime;
    WorkEndTime workEndTime;

    public static WorkTime of(WorkStartTime workStartTime, WorkEndTime workEndTime) {
        int startMinutes = workStartTime.convertToMinutes();
        int endMinutes = workEndTime.convertToMinutes();

        int grossMinutes = endMinutes - startMinutes;

        int breakTime = calculateBreakTime(startMinutes, endMinutes);

        int totalMinutes = grossMinutes - breakTime;

        int legalWorkMinutes = Math.min(totalMinutes, WorkRole.LEGAL_WORK_MINUTES);
        int overtimeMinutes = Math.max(0, totalMinutes - WorkRole.LEGAL_WORK_MINUTES);

        return new WorkTime(
                new TotalWorkingHours(legalWorkMinutes),
                new TotalOverTimeHours(overtimeMinutes),
                workStartTime,
                workEndTime
        );
    }

    private static int calculateBreakTime(int startMinutes, int endMinutes) {
        return BreakTimes.getAllBreak()
                .stream()
                .filter(breakTime -> breakTime.isInclude(startMinutes, endMinutes))
                .mapToInt(
                        BreakTime::getTotalMinutes)
                .sum();
    }
}
