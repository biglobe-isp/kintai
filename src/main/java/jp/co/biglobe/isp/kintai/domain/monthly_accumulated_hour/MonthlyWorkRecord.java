package jp.co.biglobe.isp.kintai.domain.monthly_accumulated_hour;

import jp.co.biglobe.isp.kintai.domain.attendance_record.WorkRecord;
import lombok.Value;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Value
public class MonthlyWorkRecord {
    List<WorkRecord> workRecordList;
    public MonthlyWorkRecord(Map<String, WorkRecord> workRecordMap) {
        this.workRecordList = new ArrayList<>(workRecordMap.values());;
    }
}
