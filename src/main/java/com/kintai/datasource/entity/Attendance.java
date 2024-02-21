package com.kintai.datasource.entity;

import com.kintai.datasource.value.*;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * 勤怠エンティティ
 */
public class Attendance {
    // 集計月
    @Getter
    private final TotalMonth totalMonth;

    // 勤務日
    @Getter
    private final WorkDate workDate;

    // 労働時刻
    @Getter
    private final WorkTime workTime;

    // 労働時間
    @Getter
    private final WorkMinutes workMinutes;

    // 残業時間
    @Getter
    private final OverWorkMinutes overWorkMinutes;

    // 登録日付
    @Getter
    private final LocalDateTime createLocalDate;

    public Attendance(TotalMonth totalMonth, WorkDate workDate, WorkTime workTime, WorkMinutes workMinutes, OverWorkMinutes overWorkMinutes, LocalDateTime createLocalDate) {
        this.totalMonth = totalMonth;
        this.workDate = workDate;
        this.workTime = workTime;
        this.workMinutes = workMinutes;
        this.overWorkMinutes = overWorkMinutes;
        this.createLocalDate = createLocalDate;
    }
}
