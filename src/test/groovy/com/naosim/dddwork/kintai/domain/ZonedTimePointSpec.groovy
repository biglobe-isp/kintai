package com.naosim.dddwork.kintai.domain

import com.naosim.dddwork.kintai.domain.attendance.FixtureAttendanceDate
import com.naosim.dddwork.kintai.domain.timerecord.ZonedTimePoint
import spock.lang.Specification

class ZonedTimePointSpec extends Specification {

    def "[正常系]インスタンス生成が成功するか"() {
        when:
        WrappedZonedTimePoint.get(hhmm)

        then:
        Throwable thrown = getSpecificationContext().getThrownException()
        thrown == null

        where:
        hhmm   | _
        "0000" | _
        "1901" | _
        "2359" | _
    }

    def "[異常系]インスタンス生成失敗(日付不正)"() {
        when:
        new ZonedTimePoint(FixtureAttendanceDate.get().getZonedDateTime(), hhmm)

        then:
        IllegalArgumentException e = thrown()
        e.getMessage() == errorMessage

        where:
        hhmm || errorMessage
        null || "日付か時刻が取得できませんでした。"
    }

    def "isBefore()の確認"() {
        expect:
        WrappedZonedTimePoint.get(target).isBefore(WrappedZonedTimePoint.get(comparison)) == result

        where:
        target | comparison || result
        "0959" | "1000"     || true
        "0959" | "2359"     || true
        "0959" | "0959"     || false
        "0959" | "0958"     || false
        "0959" | "0000"     || false
    }

    def "isEqualOrBefore()の確認"() {
        expect:
        WrappedZonedTimePoint.get(target).isEqualOrBefore(WrappedZonedTimePoint.get(comparison)) == result

        where:
        target | comparison || result
        "0959" | "1000"     || true
        "0959" | "2359"     || true
        "0959" | "0959"     || true
        "0959" | "0958"     || false
        "0959" | "0000"     || false
    }

    def "isAfter()の確認"() {
        expect:
        WrappedZonedTimePoint.get(target).isAfter(WrappedZonedTimePoint.get(comparison)) == result

        where:
        target | comparison || result
        "0959" | "0000"     || true
        "0959" | "0958"     || true
        "0959" | "0959"     || false
        "0959" | "1000"     || false
        "0959" | "2359"     || false
    }

    def "isEqualOrAfter()の確認"() {
        expect:
        WrappedZonedTimePoint.get(target).isEqualOrAfter(WrappedZonedTimePoint.get(comparison)) == result

        where:
        target | comparison || result
        "0959" | "0000"     || true
        "0959" | "0958"     || true
        "0959" | "0959"     || true
        "0959" | "1000"     || false
        "0959" | "2359"     || false
    }

    def "isEqual()の確認"() {
        expect:
        WrappedZonedTimePoint.get(target).isEqual(WrappedZonedTimePoint.get(comparison)) == result

        where:
        target | comparison || result
        "0959" | "0959"     || true
        "0959" | "0958"     || false
        "0959" | "1000"     || false
    }
}
