package kintai.domain;

import lombok.Value;

import java.time.Instant;
import java.util.Date;

/**
 * 勤怠エンティティ.
 */
@Value
public class Attendance {
    /**
     * 出勤日
     */
    Date date;

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
