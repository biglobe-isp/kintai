package com.naosim.dddwork.kintai.domain.core.type.time.component

import com.naosim.dddwork.kintai.shared.easy.Validatable
import com.naosim.dddwork.kintai.shared.exception.ValidationException
import spock.lang.Specification
import spock.lang.Unroll

class HourOfDayTest extends Specification {

    @Unroll
    def "OK-バリエーション"() {

        when:
        def sut = HourOfDay.of(hour)
        println(sut)

        then:
        sut.value == expectedValue

        where:
        hour || expectedValue
        0    || 0
        9    || 9
        23   || 23
        24   || 24
        25   || 25
        33   || 33
    }

    @Unroll
    def "NG-バリエーション"() {

        when:
        HourOfDay.of(hour)

        then:
        def e = thrown(ValidationException)
        e.getMessage() == Validatable.DEFAULT_ERROR_MESSAGE
        e.getErrorMessages().contains("must be between 0 and 33")

        where:
        hour || expected
        -1   || "例外発生"
        34   || "例外発生"
    }
}
