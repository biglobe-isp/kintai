package com.naosim.dddwork.kintai.domain

import com.naosim.dddwork.kintai.domain.timerecord.ZonedTimePoint
import spock.lang.Specification

import java.text.SimpleDateFormat
import java.time.ZoneId
import java.time.ZonedDateTime

class ZonedTimePointSpec extends Specification {
    def dateFormat = new SimpleDateFormat("yyyyMMdd")
    def day = ZonedDateTime.ofInstant(dateFormat.parse("20210826").toInstant(), ZoneId.systemDefault())

    def "[正常系]インスタンス生成が成功するか"() {
        when:
        def zonedTimePoint = new ZonedTimePoint(day, hhmm)

        then:
        Throwable thrown = getSpecificationContext().getThrownException()
        thrown == null

        where:
        hhmm   | _
        "0000" | _
        "1901" | _
        "2359" | _
    }

    // TODO: 型指定
//    def "[異常系]インスタンス生成失敗(日付不正)"() {
//        when:
//        new ZonedTimePoint(null, hhmm)
//
//        then:
//        Throwable e = thrown()
//        e.getMessage() == errorMessage
//
//        where:
//        hhmm || errorMessage
//        null || "日付か時刻が取得できませんでした。"
//    }

    def "[異常系]インスタンス生成失敗(時刻不正)"() {
        when:
        new ZonedTimePoint(day, hhmm)

        then:
        Throwable e = thrown()
        e.getMessage() == errorMessage

        where:
        hhmm   || errorMessage
//        null   || "日付か時刻が取得できませんでした。"
        ""     || "不正な時刻です。"
        "123"  || "不正な時刻です。"
        "2400" || "不正な時刻です。"
        "1560" || "不正な時刻です。"
        "2398" || "不正な時刻です。"
    }

    def "isBefore()の確認"() {
        expect:
        new ZonedTimePoint(day, target).isBefore(new ZonedTimePoint(day, comparison)) == result

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
        new ZonedTimePoint(day, target).isEqualOrBefore(new ZonedTimePoint(day, comparison)) == result

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
        new ZonedTimePoint(day, target).isAfter(new ZonedTimePoint(day, comparison)) == result

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
        new ZonedTimePoint(day, target).isEqualOrAfter(new ZonedTimePoint(day, comparison)) == result

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
        new ZonedTimePoint(day, target).isEqual(new ZonedTimePoint(day, comparison)) == result

        where:
        target | comparison || result
        "0959" | "0959"     || true
        "0959" | "0958"     || false
        "0959" | "1000"     || false
    }
}
