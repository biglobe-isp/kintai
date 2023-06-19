package jp.co.biglobe.isp.kintai.application;

import jp.co.biglobe.isp.kintai.domain.monthly_accumulated_hour.MonthlyAccumulatedWorkMinutes;

public class OutputMonthlyAccumulatedWorkMinutes {
    public static String toStringMonthlyAccumulatedWorkRecordMinutes(MonthlyAccumulatedWorkMinutes monthlyAccumulatedWorkMinutes) {
        return "勤務時間: " + monthlyAccumulatedWorkMinutes.getAccumulatedWorkRecordMinutes() / 60 + "時間" + monthlyAccumulatedWorkMinutes.getAccumulatedWorkRecordMinutes() % 60 + "分";
    }

    public static String toStringMonthlyAccumulatedOverTimeMinutes(MonthlyAccumulatedWorkMinutes monthlyAccumulatedWorkMinutes) {
        return "残業時間: " + monthlyAccumulatedWorkMinutes.getAccumulatedOverTImeMinutes() / 60 + "時間" + monthlyAccumulatedWorkMinutes.getAccumulatedOverTImeMinutes() % 60 + "分";
    }
}
