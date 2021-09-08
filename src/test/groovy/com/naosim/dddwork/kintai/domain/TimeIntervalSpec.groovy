package com.naosim.dddwork.kintai.domain

import com.naosim.dddwork.kintai.domain.timerecord.EndTime
import com.naosim.dddwork.kintai.domain.timerecord.StartTime
import com.naosim.dddwork.kintai.domain.timerecord.TimeInterval
import com.naosim.dddwork.kintai.domain.timerecord.TimeIntervalComparedStatus
import com.naosim.dddwork.kintai.domain.timerecord.attendance.AttendanceDate
import spock.lang.Specification
import spock.lang.Unroll
// Unroll -> #を展開させるのに必要
@Unroll
class TimeIntervalSpec extends Specification {
    def day = new AttendanceDate("20210826")

    def "[正常系]インスタンス生成が成功するか #label"() {
        when:
        def timeInterval = new TimeInterval(WrappedStartTime.get(startTime), WrappedEndTime.get(endTime))

        then:
        assert timeInterval.getStartTime() == WrappedStartTime.get(startTime)
        assert timeInterval.getEndTime()   == WrappedEndTime.get(endTime)

        where:
        label             | startTime | endTime
        "min ~ max"       | "0000"    | "2359"
        "differ 1 minute" | "0900"    | "0901"
        "same time"       | "0900"    | "0900"
    }

    def "[異常系]インスタンス生成失敗(開始終了が逆)"() {
        when:
        new TimeInterval(WrappedStartTime.get(startTime), WrappedEndTime.get(endTime))

        then:
        IllegalArgumentException e = thrown()
        e.getMessage() == errorMessage

        where:
        startTime | endTime || errorMessage
        "0900"    | "0800"  || "開始時刻が終了時刻より後になっています。" // 別時間
        "0901"    | "0900"  || "開始時刻が終了時刻より後になっています。" // 同時間 & endTimeの方が分が大きい
    }

    def "getComparedStatus()の確認"() {
        when:
        def target = new TimeInterval(new StartTime(day, targetST), new EndTime(day, targetET))
        def comparison = new TimeInterval(new StartTime(day, comparisonST), new EndTime(day, comparisonET))

        then:
        target.getComparedStatus(comparison) == result

        where:
        targetST | targetET | comparisonST | comparisonET || result
        "0900"   | "2000"   | "1200"       | "2000"       || TimeIntervalComparedStatus.CONTAIN_ALL // start:内包, end:同じ
        "0900"   | "2000"   | "0900"       | "1200"       || TimeIntervalComparedStatus.CONTAIN_ALL // start:同じ, end:内包
        "1301"   | "1401"   | "0900"       | "1401"       || TimeIntervalComparedStatus.WITHIN // start:内包されている, end:同じ
        "1301"   | "1401"   | "1301"       | "1500"       || TimeIntervalComparedStatus.WITHIN // start:同じ, end:内包されている
        "0859"   | "1959"   | "0900"       | "2000"       || TimeIntervalComparedStatus.EQUAL_OR_BEFORE // start:前, end:前
        "0900"   | "1500"   | "1500"       | "2100"       || TimeIntervalComparedStatus.EQUAL_OR_BEFORE // start:前, end:比較対象のstartと同じ
        "0900"   | "2000"   | "0859"       | "1959"       || TimeIntervalComparedStatus.EQUAL_OR_AFTER // start:後, end:後
        "1500"   | "2100"   | "0900"       | "1500"       || TimeIntervalComparedStatus.EQUAL_OR_AFTER // start:比較対象のendと同じ, end:後
        "0900"   | "1500"   | "1501"       | "2100"       || TimeIntervalComparedStatus.OUT_OF // start:前, end:比較対象のstartより前
        "1500"   | "2100"   | "0900"       | "1459"       || TimeIntervalComparedStatus.OUT_OF// start:比較対象のendより後 , end:後
    }
}
