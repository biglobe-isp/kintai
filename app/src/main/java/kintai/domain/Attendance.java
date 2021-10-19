package kintai.domain;

import lombok.Value;

import java.time.LocalDate;

/**
 * 勤怠エンティティ.
 */
@Value
public class Attendance {
    /**
     * 出勤日
     */
    LocalDate date;

    /**
     * 勤怠時間
     */
    AttendanceTime attendanceTime;

    /**
     * 労働時間
     */
    WorkDuration workDuration;

    /**
     * 残業時間
     */
    OverWorkDuration overWorkDuration;
}
