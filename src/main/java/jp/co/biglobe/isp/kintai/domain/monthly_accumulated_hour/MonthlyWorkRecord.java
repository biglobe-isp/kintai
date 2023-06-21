package jp.co.biglobe.isp.kintai.domain.monthly_accumulated_hour;

import jp.co.biglobe.isp.kintai.domain.attendance_record.WorkRecord;
import lombok.Value;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Value
public class MonthlyWorkRecord {
    List<WorkRecord> workRecordList;
    public MonthlyWorkRecord(List<WorkRecord> workRecordList) {
        Map<String, WorkRecord> workRecordMap = new HashMap<>();
        // 同じ日付が登録してある場合、後から処理する日付を登録
        for(WorkRecord workRecord: workRecordList){
            var workDateStr = workRecord.getWorkDate().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
            workRecordMap.put(workDateStr, workRecord);
        }
        this.workRecordList = new ArrayList<>(workRecordMap.values());;
    }
}
