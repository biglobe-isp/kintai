package com.kintai.datasource.value.expansion.overworkminutes

import com.kintai.datasource.value.EndTime
import com.kintai.datasource.value.StartTime
import com.kintai.datasource.value.WorkDate
import com.kintai.datasource.value.WorkTime
import com.kintai.datasource.value.expansion.workminutes.WorkMinutesExtend
import spock.lang.Specification
import spock.lang.Unroll

class OverWorkMinutesExtendSpec extends Specification {
    @Unroll
    def "正常系(#description)"() {
        when:
        WorkDate testWorkDate = new WorkDate(workDateValue)
        WorkTime testWorkTime = new WorkTime(new StartTime(startTimeValue), new EndTime(endTimeValue))
        WorkMinutesExtend testWorkMinutesExtend = new WorkMinutesExtend(testWorkTime, testWorkDate)
        OverWorkMinutesExtend actualOverWorkMinutesExtend = new OverWorkMinutesExtend(testWorkMinutesExtend, testWorkDate)

        then:
        actualOverWorkMinutesExtend.getOverWorkMinutes() == expectOverWorkMinute

        where:
        workDateValue | startTimeValue | endTimeValue || expectOverWorkMinute || description
        "20240314" | "0900" | "1800" || 0 || "休憩時間増える前日"
        "20240315" | "0900" | "1800" || 0 || "休憩時間増える当日"
        "20240316" | "0900" | "1800" || 0 || "休憩時間増える後日"
        "20240315" | "0900" | "2000" || 60 || "休憩時間増える後日,残業時間付与"
        "20240317" | "0900" | "1500" || 0 || "15時に退社"
        "20240318" | "0900" | "1530" || 0 || "15時の途中で退社"
        "20240319" | "0900" | "1600" || 0 || "16時に退社"
        "20240320" | "0900" | "1615" || 0 || "16時の途中で退社"
    }
}
