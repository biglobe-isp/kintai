package jp.co.biglobe.isp.kintai.api;

import org.springframework.cglib.core.Local;

import java.time.LocalTime;

public class WorkTimeValidator {
    public static void isValid(LocalTime openingTime, LocalTime closingTime) {
        if(openingTime.isAfter(closingTime)) {
            throw new IllegalArgumentException("勤務開始時刻は勤務終了時刻より早い時間に設定してください.");
        }
//        if(openingTime > 900) {
//            throw new IllegalArgumentException("勤務開始時刻は9:00より前に設定してください. でないとクビです.");
//        }
//        if(closingTime > 2359) {
//            throw new IllegalArgumentException("勤務終了時刻は23:59までです.");
//        }
    }
}
