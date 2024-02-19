package com.kintai.datasource.value

import spock.lang.Specification
import spock.lang.Unroll

class WorkMinutesSpec extends Specification {
    def "正常系(デフォルトコンストラクタ)"() {
        when:
        WorkMinutes actualWorkMinutes = new WorkMinutes(480)

        then:
        actualWorkMinutes.getWorkMinutes() == 480
    }

    @Unroll
    def "正常系(#description)"() {
        setup:
        WorkTime actualWorkTime = new WorkTime(new StartTime(startTime), new EndTime(endTime))
        WorkMinutes actualWorkMinutes = new WorkMinutes(actualWorkTime)

        expect:
        workMinutes == actualWorkMinutes.getWorkMinutes()

        where:
        startTime | endTime || workMinutes || description
        "0900" | "1200" | 180 || "午前のみ出勤"
        "0900" | "1300" | 180 || "1時間休憩あり(13時退社)"
        "0900" | "1400" | 240 || "1時間休憩あり(14時退社)"
        "0900" | "1800" | 480 || "定時退社"
        "0900" | "1900" | 480 || "2時間休憩あり(19時退社)"
        "0900" | "2000" | 540 || "2時間休憩あり(20時退社)"
        "0900" | "2100" | 600 || "残業あり"
        "0900" | "2200" | 600 || "3時間休憩あり(22時退社)"
        "0900" | "2300" | 660 || "3時間休憩あり(23時退社)"
        "0900" | "1915" | 495 || "残業15分"
        "0900" | "1930" | 510 || "残業30分"
        "0900" | "1945" | 525 || "残業45分"
    }

    def "正常系(労働時間集計)"() {
        when:
        WorkMinutes workMinutes1 = new WorkMinutes(480)
        WorkMinutes workMinutes2 = new WorkMinutes(540)
        List<WorkMinutes> workMinutesList = Arrays.asList(workMinutes1,workMinutes2)

        WorkMinutes testWorkMinutes = new WorkMinutes(workMinutesList)

        then: "集計された勤務時間が登録されていること"
        1020 == testWorkMinutes.getWorkMinutes()
    }
}
