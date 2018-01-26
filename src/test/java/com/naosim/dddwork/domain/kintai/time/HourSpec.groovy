package com.naosim.dddwork.domain.kintai.time

import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification

@ContextConfiguration(locations = ["classpath:context.xml"])
class HourSpec extends Specification {

    def "時データの境界値チェック（下限）"() {
        when:
        new Hour(0)

        then:
        true
    }

    def "時データの境界値チェック（上限）"() {
        when:
        new Hour(23)

        then:
        true
    }

    def "時データの境界値チェック（範囲外：時が下限を下回った場合）"() {
        when:
        new Hour(-1)

        then:
        def e = thrown(RuntimeException)
        println(e.message)
    }

    def "時データの境界値チェック（範囲外：時が上限を上回った場合）"() {
        when:
        new Hour(24)

        then:
        def e = thrown(RuntimeException)
        println(e.message)
    }

    def "時文字列が正常に取得できる"() {
        when:
        int input = 10;
        Hour hour = new Hour(input)


        then:
        hour.toString() == input.toString()
    }

}