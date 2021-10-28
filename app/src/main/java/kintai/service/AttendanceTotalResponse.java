package kintai.service;

import lombok.Value;

@Value
public class AttendanceTotalResponse {
    Long totalWorkMinutes;
    Long totalOverWorkMinutes;
}
