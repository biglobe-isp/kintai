package jp.co.biglobe.isp.kintai.domain.monthly_accumulated_hour;

import jp.co.biglobe.isp.kintai.domain.attendance_record.WorkRecord;
import lombok.Value;

import java.util.ArrayList;
import java.util.List;

@Value
public class MonthlyAccumulatedWorkMinutes {
    private int accumulatedWorkRecordMinutes;
    private int accumulatedOverTImeMinutes;

    public static MonthlyAccumulatedWorkMinutes from(MonthlyWorkRecord monthlyWorkRecord) {
        var workRecordList = monthlyWorkRecord.getWorkRecordList();
        var accumulatedWorkRecordMinutes = workRecordList.stream().mapToInt(WorkRecord::getWorkRecordMinutes).sum();
        var accumulatedOverTimeMinutes = workRecordList.stream().mapToInt(WorkRecord::getOverTimeMinutes).sum();
        return new MonthlyAccumulatedWorkMinutes(accumulatedWorkRecordMinutes, accumulatedOverTimeMinutes);
    }
}
