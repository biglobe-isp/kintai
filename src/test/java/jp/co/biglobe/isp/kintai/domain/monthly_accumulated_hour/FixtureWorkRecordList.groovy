package jp.co.biglobe.isp.kintai.domain.monthly_accumulated_hour

import jp.co.biglobe.isp.kintai.domain.attendance_record.FixtureWorkRecord
import jp.co.biglobe.isp.kintai.domain.attendance_record.WorkRecord

import java.time.format.DateTimeFormatter

class FixtureWorkRecordList {
    static Map<String, WorkRecord> get() {
        Map<String, WorkRecord> workRecordHashMap = new HashMap<String, WorkRecord>()
        String yearMonth = FixtureWorkRecord.getJustTime().workDate.format(DateTimeFormatter.ofPattern("yyyyMMdd")).substring(0,6)
        workRecordHashMap.put(yearMonth, FixtureWorkRecord.getJustTime())

        return workRecordHashMap
    }
}
