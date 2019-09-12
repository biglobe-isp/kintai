package kintai.api;

import kintai.domain.AttendanceStartAndEndTime;
import kintai.domain.EndTime;
import kintai.domain.StartTime;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
public class AttendanceInputRequest {

    private final String date;
    private final String start;
    private final String end;

    public AttendanceStartAndEndTime getEntity() {
        LocalDate attendanceDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyyMMdd"));
        StartTime startTime = StartTime.parse(start, DateTimeFormatter.ofPattern("HHmm"));
        EndTime endTime = EndTime.parse(end, DateTimeFormatter.ofPattern("HHmm"));
        AttendanceStartAndEndTime attendanceStartAndEndTime = new AttendanceStartAndEndTime(attendanceDate, startTime, endTime);
        return attendanceStartAndEndTime;
    }
}
