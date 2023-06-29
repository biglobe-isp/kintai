package jp.co.biglobe.isp.kintai.domain.work_record


import java.time.LocalTime

class FixtureWorkTime {
    static JustTime() {
        new WorkTime(LocalTime.of(9, 0), LocalTime.of(18,0))
    }

    static EarlyOpeningTime() {
        new WorkTime(LocalTime.of(8, 0), LocalTime.of(18,0))
    }

    static EarlyClosingTimeAt1200() {
        new WorkTime(LocalTime.of(9, 0), LocalTime.of(12,0))
    }

    static EarlyClosingTimeAt1230() {
        new WorkTime(LocalTime.of(9, 0), LocalTime.of(12,30))
    }

    static EarlyClosingTimeAt1300() {
        new WorkTime(LocalTime.of(9, 0), LocalTime.of(13,0))
    }

    static EarlyClosingTimeAt1330() {
        new WorkTime(LocalTime.of(9, 0), LocalTime.of(13,30))
    }

    static LateClosingTimeAt1830() {
        new WorkTime(LocalTime.of(9, 0), LocalTime.of(18,30))
    }

    static LateClosingTimeAt1900() {
        new WorkTime(LocalTime.of(9, 0), LocalTime.of(19,00))
    }

    static LateClosingTimeAt1930() {
        new WorkTime(LocalTime.of(9, 0), LocalTime.of(19,30))
    }

    static LateClosingTimeAt2100() {
        new WorkTime(LocalTime.of(9, 0), LocalTime.of(21,00))
    }

    static LateClosingTimeAt2130() {
        new WorkTime(LocalTime.of(9, 0), LocalTime.of(21,30))
    }

    static LateClosingTimeAt2200() {
        new WorkTime(LocalTime.of(9, 0), LocalTime.of(22,00))
    }

    static LateClosingTimeAt2230() {
        new WorkTime(LocalTime.of(9, 0), LocalTime.of(22,30))
    }

    static LateClosingTimeAt2300() {
        new WorkTime(LocalTime.of(9, 0), LocalTime.of(23,00))
    }

}
