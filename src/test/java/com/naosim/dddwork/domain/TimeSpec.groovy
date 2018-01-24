package com.naosim.dddwork.domain

import com.naosim.dddwork.domain.time.Time
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification

@ContextConfiguration(locations = ["classpath:context.xml"])
class TimeSpec extends Specification {

    def "時間データの境界値チェック（下限）"() {
        when:
        new Time(0, 0);

        then:
        true
    }

    def "時間データの境界値チェック（上限）"() {
        when:
        new Time(23, 59)

        then:
        true
    }

    def "時間データの境界値チェック（範囲外：時間が上限を上回った場合）"() {
        when:
        new Time(24, 0)

        then:
        def e = thrown(RuntimeException)
        println(e.message)
    }

    def "時間データの境界値チェック（範囲外：時間が下限を下回った場合）"() {
        when:
        new Time(-1, 0)

        then:
        def e = thrown(RuntimeException)
        println(e.message)
    }

    def "時間データの境界値チェック（範囲外：分が上限を上回った場合）"() {
        when:
        new Time(0, 60)

        then:
        def e = thrown(RuntimeException)
        println(e.message)
    }

    def "時間データの境界値チェック（範囲外：分が下限を下回った場合）"() {
        when:
        new Time(0, -1)

        then:
        def e = thrown(RuntimeException)
        println(e.message)
    }

}
