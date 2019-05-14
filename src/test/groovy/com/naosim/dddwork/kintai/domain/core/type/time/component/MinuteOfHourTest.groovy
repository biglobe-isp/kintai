package com.naosim.dddwork.kintai.domain.core.type.time.component

import com.naosim.dddwork.kintai.shared.easy.Validatable
import com.naosim.dddwork.kintai.shared.exception.ValidationException
import spock.lang.Specification
import spock.lang.Unroll

class MinuteOfHourTest extends Specification {

    @Unroll
    def "OK-バリエーション"() {

        when:
        def sut = MinuteOfHour.of(minute)
        println(sut)

        then:
        sut.value == expectedValue

        where:
        minute || expectedValue
        0      || 0
        9      || 9
        46     || 46
        59     || 59
    }

    @Unroll
    def "NG-バリエーション"() {

        when:
        MinuteOfHour.of(minute)

        then:
        def e = thrown(ValidationException)
        e.getMessage() == Validatable.DEFAULT_ERROR_MESSAGE
        e.getErrorMessages().contains("must be between 0 and 59")

        where:
        minute || expected
        -1     || "例外発生"
        60     || "例外発生"
    }
}
