package jp.co.biglobe.isp.kintai.domain.monthly_accumulated_hour

import jp.co.biglobe.isp.kintai.domain.attendance_record.FixtureWorkRecord
import jp.co.biglobe.isp.kintai.domain.attendance_record.WorkRecord

import java.time.format.DateTimeFormatter

class FixtureWorkRecordList {
    static List<WorkRecord> get() {
        List<WorkRecord> workRecordList = new ArrayList<>()

        workRecordList.add(FixtureWorkRecord.getJustTime())
        workRecordList.add(FixtureWorkRecord.getEarlyOpeningTime())
        workRecordList.add(FixtureWorkRecord.getLateClosingTimeAt1830())

        return workRecordList
    }
}
