package kintai.domain;

import lombok.Value;

import java.time.LocalDateTime;

/**
 * 勤怠時間.
 */
@Value
public class AttendanceTime {
    /**
     * 出社時間
     */
    LocalDateTime start;

    /**
     * 退勤時間
     */
    LocalDateTime end;
}
