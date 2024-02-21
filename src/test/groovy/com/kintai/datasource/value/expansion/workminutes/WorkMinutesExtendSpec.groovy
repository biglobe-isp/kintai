package com.kintai.datasource.value.expansion.workminutes

import com.kintai.datasource.value.EndTime
import com.kintai.datasource.value.StartTime
import com.kintai.datasource.value.WorkDate
import com.kintai.datasource.value.WorkTime
import spock.lang.Specification
import spock.lang.Unroll

class WorkMinutesExtendSpec extends Specification {
    @Unroll
    def "正常系(#description)"() {
        when:
        WorkDate testWorkDate = new WorkDate(workDateValue)
        WorkTime testWorkTime = new WorkTime(new StartTime(startTimeValue), new EndTime(endTimeValue))
        WorkMinutesExtend actualWorkMinutesExtend = new WorkMinutesExtend(testWorkTime, testWorkDate)

        then:
        actualWorkMinutesExtend.getWorkMinutes() == expectWorkMinutes

        where:
        workDateValue | startTimeValue | endTimeValue || expectWorkMinutes || description
        "20240314" | "0900" | "1800" || 480 || "休憩時間増える前日"
        "20240315" | "0900" | "1800" || 420 || "休憩時間増える当日"
        "20240316" | "0900" | "1800" || 420 || "休憩時間増える後日"
        "20240317" | "0900" | "1500" || 300 || "15時に退社"
        "20240318" | "0900" | "1530" || 300 || "15時の途中で退社"
        "20240319" | "0900" | "1600" || 300 || "16時に退社"
        "20240320" | "0900" | "1615" || 315 || "16時の途中で退社"
    }
}
