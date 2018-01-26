package com.naosim.dddwork.domain.kintai.time

import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification

@ContextConfiguration(locations = ["classpath:context.xml"])
class NowSpec extends Specification {

    def "現在時刻オブジェクトが正常に生成される"() {
        when:
        new Now()

        then:
        true
    }

    def "文字列から生成した現在時刻オブジェクトの返却結果が正しい"() {
        setup:
        String input = "2018-01-25T10:07:49.546"

        when:
        Now now = new Now(input)

        then:
        now.getValue() == input
    }
}
