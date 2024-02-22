package com.kintai.datasource.entity;

import com.kintai.datasource.value.*;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * 勤怠エンティティ
 * 勤怠に関係のある値を管理します。
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

    /*
    勤怠データを登録したシステム日時を管理するオブジェクトです。
     */
    @Getter
    private final LocalDateTime createLocalDate;

    /**
     * 勤怠データを格納するコンストラクタ
     * @param totalMonth 集計月
     * @param workDate 勤怠日
     * @param workTime 勤怠時刻
     * @param workMinutes 労働時間
     * @param overWorkMinutes 残業時間
     * @param createLocalDate 登録日時
     */
    public Attendance(TotalMonth totalMonth, WorkDate workDate, WorkTime workTime, WorkMinutes workMinutes, OverWorkMinutes overWorkMinutes, LocalDateTime createLocalDate) {
        this.totalMonth = totalMonth;
        this.workDate = workDate;
        this.workTime = workTime;
        this.workMinutes = workMinutes;
        this.overWorkMinutes = overWorkMinutes;
        this.createLocalDate = createLocalDate;
    }
}
