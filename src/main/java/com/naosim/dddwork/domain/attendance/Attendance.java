package com.naosim.dddwork.domain.attendance;

import com.naosim.dddwork.domain.TimePoint;
import com.naosim.dddwork.domain.workregulations.WorkRegulations;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@RequiredArgsConstructor
@EqualsAndHashCode
public class Attendance {

    @Getter
    private final LocalDate workDay;

    @Getter
    private AttendanceTime attendanceTime;

    @Getter
    private WorkingHours workingHours;

    @Getter
    private OverTimeHours overTimeHours;

    public static Attendance of(String workD, String start, String end, String workingH, String overTimeH,
                                WorkRegulations workRegulations) {
        return new Attendance(workD, start, end, workingH, overTimeH, workRegulations);
    }

    // TODO:ファクトリメソッドを用意する。attendanceパッケージ全体？
    public Attendance(String workD, String start, String end, String workingH, String overTimeH,
                      WorkRegulations workRegulations) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        this.workDay = LocalDate.parse(workD, formatter);

        this.attendanceTime = AttendanceTime.of(
                StartTime.of(TimePoint.of(start)), EndTime.of(TimePoint.of(end)));

        this.workingHours =  WorkingHours.of(this.attendanceTime,
                                             BreakTimeHours.of(attendanceTime, workRegulations));

        this.overTimeHours = OverTimeHours.of(this.workingHours, workRegulations);
    }
}
