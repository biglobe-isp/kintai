package jp.co.biglobe.isp.kintai.domain.monthly_accumulated_hour;

import io.vavr.collection.List;
import jp.co.biglobe.isp.kintai.domain.work_record.WorkRecord;
import lombok.Value;

@Value
public class MonthlyAccumulatedWorkMinutes {
    int accumulatedWorkRecordMinutes;
    int accumulatedOverTImeMinutes;

    public static MonthlyAccumulatedWorkMinutes from(List<WorkRecord> monthlyWorkRecordList) {
        var accumulatedWorkRecordMinutes = monthlyWorkRecordList.map(WorkRecord::getWorkRecordMinutes).sum();
        var accumulatedOverTimeMinutes = monthlyWorkRecordList.map(WorkRecord::getOverTimeMinutes).sum();
        return new MonthlyAccumulatedWorkMinutes(accumulatedWorkRecordMinutes.intValue(), accumulatedOverTimeMinutes.intValue());
    }
}
