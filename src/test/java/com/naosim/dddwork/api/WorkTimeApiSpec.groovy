package com.naosim.dddwork.api

import spock.lang.Specification

class WorkTimeApiSpec extends Specification {
    def workTimeApi

    def setup() {
        workTimeApi = new WorkTimeApi()
    }

    def パラメータチェック() {
        setup:
        String[] args = new String()[]

        when:
        workTimeApi.workTimeCalculate(args)

        then:
        def ex = thrown(RuntimeException)

    }

    def 機能チエック() {
        setup:
        String[] args = ["inputabcd", "20170101", "0900"]

        when:
        workTimeApi.workTimeCalculate(args)

        then:
        def ex = thrown(RuntimeException)

    }

    def 勤怠入力機能パラメータチェック_異常パターン() {
        setup:
        String[] args = ["input", "20170101", "0900"]

        when:
        workTimeApi.workTimeCalculate(args)

        then:
        def ex = thrown(RuntimeException)
    }

    def 勤怠入力機能_パラメータチェック_正常パターン() {
        setup:
        String[] args = ["input", "20170101", "0900", "1800"]

        when:
        workTimeApi.workTimeCalculate(args)

        then:
        true

    }

    def 勤怠合計時間パラメータチェック_異常パターン() {
        setup:
        String[] args = ["total"]

        when:
        workTimeApi.workTimeCalculate(args)

        then:
        def ex = thrown(RuntimeException)

    }

    def 勤怠合計時間パラメータチェック_正常パターン() {
        setup:
        String[] args = ["total", "201701"]

        when:
        workTimeApi.workTimeCalculate(args)

        then:
        true

    }
}
