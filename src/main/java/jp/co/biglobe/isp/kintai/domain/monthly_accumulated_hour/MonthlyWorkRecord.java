package jp.co.biglobe.isp.kintai.domain.monthly_accumulated_hour;

import io.vavr.collection.List;
import jp.co.biglobe.isp.kintai.domain.work_record.WorkRecord;
import lombok.Value;

import java.time.format.DateTimeFormatter;

@Value
public class MonthlyWorkRecord {
    List<WorkRecord> workRecordList;
    public MonthlyWorkRecord(List<WorkRecord> workRecordList) {
        // 同じ日付が登録してある場合、後から処理する日付を登録
        var workRecordMap = workRecordList.toMap(x -> x.getWorkDate().format(DateTimeFormatter.ofPattern("yyyyMMdd")), x -> x);
        this.workRecordList = workRecordMap.values().toList();
    }
}
