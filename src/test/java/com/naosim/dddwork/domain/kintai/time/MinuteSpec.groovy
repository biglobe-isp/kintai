package com.naosim.dddwork.domain.kintai.time

import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification

@ContextConfiguration(locations = ["classpath:context.xml"])
class MinuteSpec extends Specification {

    def "分オブジェクトが正常に生成される"() {
        when:
        new Minute(0)

        then:
        true
    }

    def "分文字列が正常に取得できる"() {
        when:
        int input = 0
        Minute minute = new Minute(input)

        then:
        minute.toString() == input.toString()
        System.out.println()
    }
}
