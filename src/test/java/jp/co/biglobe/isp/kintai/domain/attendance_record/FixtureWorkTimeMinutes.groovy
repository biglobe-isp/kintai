package jp.co.biglobe.isp.kintai.domain.attendance_record

import java.time.LocalTime

class FixtureWorkTimeMinutes {
    static regulatedJustTime() {
        60 * 9
    }

    static regulatedEarlyOpeningTime() {
        60 * 10
    }

    static EarlyClosingTimeAt1200() {
        60 * 3
    }

    static LateClosingTimeAt1900() {
        60 * 10
    }
}
