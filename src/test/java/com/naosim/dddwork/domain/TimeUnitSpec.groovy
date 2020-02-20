package com.naosim.dddwork.domain

import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification
import spock.lang.Unroll

@SpringBootTest
class TimeUnitSpec extends Specification {

    @Unroll
    def "正常系テスト"() {
        setup:
        def timeUnit = new TimeUnit(inputTime)

        expect:
        timeUnit.isTimeValue(inputTime) == validTime
        timeUnit.getHour() == hour
        timeUnit.getMinutes() == minutes

        where:
        inputTime || validTime | hour | minutes
        "0000"    || true      | 0    | 0
        "0125"    || true      | 1    | 25
        "2359"    || true      | 23   | 59
        "2400"    || true      | 24   | 00
        "2730"    || true      | 27   | 30
        "2959"    || true      | 29   | 59
    }

    def "Nullはエラー"() {
        when:
        def timeUnit = new TimeUnit(null)

        then:
        thrown(RuntimeException)
    }

    def "空文字はエラー"() {
        when:
        def timeUnit = new TimeUnit("")

        then:
        thrown(RuntimeException)
    }

    def "翌朝6時以降はエラー"() {
        when:
        def timeUnit = new TimeUnit("3000")

        then:
        thrown(RuntimeException)
    }

    def "時間が不正の場合はエラー"() {
        when:
        def timeUnit = new TimeUnit("2360")

        then:
        thrown(RuntimeException)
    }

    def "4桁未満の時間はエラー"() {
        when:
        def timeUnit = new TimeUnit("920")

        then:
        thrown(RuntimeException)
    }

    def "5桁以上はエラー"() {
        when:
        def timeUnit = new TimeUnit("10900")

        then:
        thrown(RuntimeException)
    }
}
