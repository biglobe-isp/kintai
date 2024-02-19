package com.kintai.datasource.entity;

import com.kintai.datasource.value.*;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * 勤怠エンティティ
 */
public class Attendance {
    @Getter
    private final TotalMonth totalMonth;

    @Getter
    private final WorkDate workDate;

    @Getter
    private final WorkTime workTime;

    @Getter
    private final WorkMinutes workMinutes;

    @Getter
    private final OverWorkMinutes overWorkMinutes;

    @Getter
    private final LocalDateTime localDateTime;

    public Attendance(TotalMonth totalMonth, WorkDate workDate, WorkTime workTime, WorkMinutes workMinutes, OverWorkMinutes overWorkMinutes, LocalDateTime localDateTime) {
        this.totalMonth = totalMonth;
        this.workDate = workDate;
        this.workTime = workTime;
        this.workMinutes = workMinutes;
        this.overWorkMinutes = overWorkMinutes;
        this.localDateTime = localDateTime;
    }
}
