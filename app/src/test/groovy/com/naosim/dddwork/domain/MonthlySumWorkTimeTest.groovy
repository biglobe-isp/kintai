package com.naosim.dddwork.domain

import spock.lang.Specification

class MonthlySumWorkTimeTest extends Specification {
    def "CalculateTotalWorkMonthTime"() {
        given:
        def sut = new MonthlySumWorkTime()
        def expected = 100
        def storeWorkTimeList = [new WorkTimeStorage(100, 0)]

        when:
        def actual = sut.calculateTotalWorkMonthTime(storeWorkTimeList)

        then:
        actual == expected
    }

    def "CalculateTotalOverWorkMonthTime"() {
        given:
        def sut = new MonthlySumWorkTime()
        def expected = 100
        def storeWorkTimeList = [new WorkTimeStorage(0, 100)]

        when:
        def actual = sut.calculateTotalOverWorkMonthTime(storeWorkTimeList)

        then:
        actual == expected
    }
}
