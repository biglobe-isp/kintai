package com.kintai.datasource.value;

import lombok.Getter;

/**
 * 集計月のvalueオブジェクト
 */
public class TotalMonth {
    /* 集計月 */
    @Getter
    private String totalMonth;

    public TotalMonth(String totalMonth) {
        this.totalMonth = totalMonth;
    }

    /**
     * 勤務月の年月部分のみを切り取り
     *
     * @param workDate
     * @return
     */
    public TotalMonth(WorkDate workDate) {
        /* 本アプリでは、勤務月は勤務日オブジェクトから切り取りする。
        * 勤務日にて、既に形式チェックは行っているので、本メソッドではチェックは行わないものとする。
        *  */
        this.totalMonth = workDate.getWorkDate().substring(0,6);
    }
}
