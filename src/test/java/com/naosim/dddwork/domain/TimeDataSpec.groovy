package com.naosim.dddwork.domain

import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification

@ContextConfiguration(locations = ["classpath:context.xml"])
class TimeDataSpec extends Specification {

    def "時間データの境界値チェック（下限）"() {
        when:
        TimeData timeData = new TimeData(0, 0);

        then:
        true
    }

    def "時間データの境界値チェック（上限）"() {
        when:
        TimeData timeData = new TimeData(23, 59)

        then:
        true
    }

    def "時間データの境界値チェック（範囲外：時間が上限を上回った場合）"() {
        when:
        TimeData timeData = new TimeData(24, 0)

        then:
        def e = thrown(RuntimeException)
        println(e.message)
    }

    def "時間データの境界値チェック（範囲外：時間が下限を下回った場合）"() {
        when:
        TimeData timeData = new TimeData(-1, 0)

        then:
        def e = thrown(RuntimeException)
        println(e.message)
    }

    def "時間データの境界値チェック（範囲外：分が上限を上回った場合）"() {
        when:
        TimeData timeData = new TimeData(0, 60)

        then:
        def e = thrown(RuntimeException)
        println(e.message)
    }

    def "時間データの境界値チェック（範囲外：分が下限を下回った場合）"() {
        when:
        TimeData timeData = new TimeData(0, -1)

        then:
        def e = thrown(RuntimeException)
        println(e.message)
    }

}
