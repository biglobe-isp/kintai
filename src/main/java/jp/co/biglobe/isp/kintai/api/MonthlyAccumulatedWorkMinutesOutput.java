package jp.co.biglobe.isp.kintai.api;

import jp.co.biglobe.isp.kintai.domain.monthly_accumulated_hour.MonthlyAccumulatedWorkMinutes;
import lombok.Value;

@Value
public class MonthlyAccumulatedWorkMinutesOutput {
    MonthlyAccumulatedWorkMinutes monthlyAccumulatedWorkMinutes;
    @Override
    public String toString() {
        return "勤務時間: " + monthlyAccumulatedWorkMinutes.getAccumulatedWorkRecordMinutes() / 60 + "時間" + monthlyAccumulatedWorkMinutes.getAccumulatedWorkRecordMinutes() % 60 + "分" + "\n" +
        "残業時間: " + monthlyAccumulatedWorkMinutes.getAccumulatedOverTImeMinutes() / 60 + "時間" + monthlyAccumulatedWorkMinutes.getAccumulatedOverTImeMinutes() % 60 + "分";
    }
}
