package org.example.domain;

import lombok.Value;

@Value
public class WorkTime {
    WorkingHours workingHours;
    Overtime overtime;
    StartTime startTime;
    EndTime endTime;

    public static WorkTime of(StartTime startTime, EndTime endTime) {
        int startMinutes = startTime.convertToMinutes().getValue();
        int endMinutes = endTime.convertToMinutes().getValue();

        int grossMinutes = endMinutes - startMinutes;

        int breakTime = calculateBreakTime(startMinutes, endMinutes);

        int totalMinutes = grossMinutes - breakTime;

        int legalWorkMinutes = Math.min(totalMinutes, WorkRole.LEGAL_WORK_MINUTES);
        int overtimeMinutes = Math.max(0, totalMinutes - WorkRole.LEGAL_WORK_MINUTES);

        return new WorkTime(
                new WorkingHours(new Time(legalWorkMinutes)),
                new Overtime(new Time(overtimeMinutes)),
                startTime,
                endTime
        );
    }

    private static int calculateBreakTime(int startMinutes, int endMinutes) {
        return WorkRole.getAllBreak()
                .stream()
                .filter(breakTime -> breakTime.isInclude(startMinutes, endMinutes))
                .mapToInt(
                        BreakTime::getTotalMinutes)
                .sum();
    }
}
