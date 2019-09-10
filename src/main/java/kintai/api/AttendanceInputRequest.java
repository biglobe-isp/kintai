package kintai.api;

import kintai.domain.AttendanceStartAndEndTime;
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
        LocalTime startTime = LocalTime.parse(start, DateTimeFormatter.ofPattern("HHmm"));
        LocalTime endTime = LocalTime.parse(end, DateTimeFormatter.ofPattern("HHmm"));
        AttendanceStartAndEndTime attendanceStartAndEndTime = new AttendanceStartAndEndTime(attendanceDate, startTime, endTime);
        return attendanceStartAndEndTime;
    }
}
