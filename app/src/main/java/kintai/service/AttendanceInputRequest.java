package kintai.service;

import lombok.Value;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Value
public class AttendanceInputRequest {
    LocalDate date;
    LocalDateTime start;
    LocalDateTime end;

    public AttendanceInputRequest(String date, String start, String end) {
        this.date = LocalDate.of(
                Integer.parseInt(date.substring(0,4)),
                Integer.parseInt(date.substring(4,6)),
                Integer.parseInt(date.substring(6,8)));
        this.start = LocalDateTime.of(this.date,
                                      LocalTime.of(
                                              Integer.parseInt(start.substring(0, 2)),
                                              Integer.parseInt(start.substring(2,4))));
        this.end = LocalDateTime.of(this.date,
                                    LocalTime.of(
                                            Integer.parseInt(end.substring(0, 2)),
                                            Integer.parseInt(end.substring(2,4))));
    }
}
