package com.kintai.datasource.entity;

import com.kintai.datasource.value.OverWorkMinutes;
import com.kintai.datasource.value.TotalMonth;
import com.kintai.datasource.value.WorkMinutes;
import lombok.Getter;

/**
 * 労働集計エンティティ
 * 勤怠データを集計した結果を管理するエンティティクラスです。
 */
public class WorkTotal {
    // 集計月
    @Getter
    private final TotalMonth totalMonth;

    // 労働時間
    @Getter
    private final WorkMinutes workMinutes;

    // 残業時間
    @Getter
    private final OverWorkMinutes overWorkMinutes;

    /**
     * コンストラクタ
     * @param totalMonth 集計月
     * @param workMinutes 労働時間
     * @param overWorkMinutes 残業時間
     */
    public WorkTotal(TotalMonth totalMonth, WorkMinutes workMinutes, OverWorkMinutes overWorkMinutes) {
        this.totalMonth = totalMonth;
        this.workMinutes = workMinutes;
        this.overWorkMinutes = overWorkMinutes;
    }
}
