package jp.co.biglobe.isp.kintai.domain.attendance_record

import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class FixtureWorkRecord {
    static WorkRecord getJustTime() {
       new WorkRecord(LocalDate.parse("20230101", DateTimeFormatter.ofPattern("yyyyMMdd")),
                FixtureWorkTime.JustTime(),
                FixtureWorkRecordMinutes.JustTime(),
                FixtureOverTimeMinutes.JustTime()
       )
    }
}
