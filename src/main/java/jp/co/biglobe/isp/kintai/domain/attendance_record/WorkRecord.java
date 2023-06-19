package jp.co.biglobe.isp.kintai.domain.attendance_record;

import lombok.Value;

import java.time.LocalDate;

/**
 * 勤務実績
 */

@Value
public class WorkRecord {
    LocalDate workDate;
    WorkTime workTime;
    int workRecordMinutes;
    int overTimeMinutes;
}
