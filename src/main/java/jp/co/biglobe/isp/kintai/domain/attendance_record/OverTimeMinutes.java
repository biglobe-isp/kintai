package jp.co.biglobe.isp.kintai.domain.attendance_record;

import lombok.Value;

import static java.lang.Math.max;

@Value
public class OverTimeMinutes {
    public static int from(int workRecordMinutes, int regulatedWorkMinutes) {
        // 規定業務時間より長く働けば、右の値は0分以上になる. 短くなれば、右の値はマイナスになり残業時間は0
        var overTimeMinutes = max(0, workRecordMinutes - regulatedWorkMinutes);
        return overTimeMinutes;
    }
}
