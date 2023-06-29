package jp.co.biglobe.isp.kintai.domain.work_record

import java.time.LocalDate
import java.time.format.DateTimeFormatter

class FixtureWorkRecord {
    static WorkRecord getJustTime() {
       new WorkRecord(LocalDate.parse("20230101", DateTimeFormatter.ofPattern("yyyyMMdd")),
                FixtureWorkTime.JustTime(),
                FixtureWorkRecordMinutes.JustTime(),
                FixtureOverTimeMinutes.JustTime()
       )
    }

    static WorkRecord getEarlyOpeningTime() {
        new WorkRecord(LocalDate.parse("20230102", DateTimeFormatter.ofPattern("yyyyMMdd")),
                FixtureWorkTime.EarlyOpeningTime(),
                FixtureWorkRecordMinutes.EarlyOpeningTime(),
                FixtureOverTimeMinutes.EarlyOpeningTime()
        )
    }

    static WorkRecord getLateClosingTimeAt1830() {
        new WorkRecord(LocalDate.parse("20230103", DateTimeFormatter.ofPattern("yyyyMMdd")),
                FixtureWorkTime.LateClosingTimeAt1830(),
                FixtureWorkRecordMinutes.LateClosingTimeAt1830(),
                FixtureOverTimeMinutes.LateClosingTimeAt1830()
        )
    }
}
