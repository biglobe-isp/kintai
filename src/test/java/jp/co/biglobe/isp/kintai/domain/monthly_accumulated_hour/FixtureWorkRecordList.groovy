package jp.co.biglobe.isp.kintai.domain.monthly_accumulated_hour

import io.vavr.collection.List
import jp.co.biglobe.isp.kintai.domain.work_record.FixtureWorkRecord
import jp.co.biglobe.isp.kintai.domain.work_record.WorkRecord

class FixtureWorkRecordList {
    static List<WorkRecord> get() {
        List<WorkRecord> workRecordList = List.of(
                FixtureWorkRecord.getJustTime(),
                FixtureWorkRecord.getEarlyOpeningTime(),
                FixtureWorkRecord.getLateClosingTimeAt1830()
        )
        return workRecordList
    }
}
