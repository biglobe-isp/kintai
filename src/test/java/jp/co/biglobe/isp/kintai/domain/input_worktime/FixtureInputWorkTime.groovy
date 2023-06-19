package jp.co.biglobe.isp.kintai.domain.input_worktime

import jp.co.biglobe.isp.kintai.domain.attendance_record.FixtureWorkTime

import java.time.LocalDate
import java.time.format.DateTimeFormatter

class FixtureInputWorkTime {
    static InputWorkTime get() {
        LocalDate workDate = LocalDate.parse("20230101", DateTimeFormatter.ofPattern("yyyyMMdd"))
        return new InputWorkTime(workDate, FixtureWorkTime.JustTime())
    }
}
