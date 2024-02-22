package com.kintai.datasource.value;

import lombok.Getter;

/**
 * 勤怠管理として集計月を管理するValue Object
 */
public class TotalMonth {
    // 集計月の値
    @Getter
    protected String totalMonth;

    /**
     * デフォルトコンストラクタ
     * @param totalMonth 集計月
     */
    public TotalMonth(String totalMonth) {
        this.totalMonth = totalMonth;
    }

    /**
     * 勤務日から年月部分のみを切り取るコンストラクタ
     * @param workDate 勤務日
     */
    public TotalMonth(WorkDate workDate) {
        /* 本アプリでは、勤務月は勤務日オブジェクトから切り取りする。
        * 勤務日にて、既に形式チェックは行っているので、本メソッドではチェックは行わないものとする。
        *  */
        this.totalMonth = workDate.getWorkDate().substring(0,6);
    }
}
