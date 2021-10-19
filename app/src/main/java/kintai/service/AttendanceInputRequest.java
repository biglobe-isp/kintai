package kintai.service;

import lombok.Value;

@Value
public class AttendanceInputRequest {
    String date;
    String start;
    String end;
}
