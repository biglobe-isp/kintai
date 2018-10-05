package com.naosim.dddwork.utility;

import java.time.LocalTime;

/**
 * 休憩時間
 * 2018/10/05 新規作成
 * 　　　レビュー指摘事項反映 LocalTime関連のメソッドを共通部品化
 */
public class TimeUtility {

    public static LocalTime earlyTime(LocalTime one, LocalTime other) {
        if (one.isBefore(other)) {
            return one;
        }
        return other;
    }

    public static LocalTime lateTime(LocalTime one, LocalTime other) {
        if (one.isAfter(other)) {
            return one;
        }
        return other;
    }
}
