package com.sample.ddd

import spock.lang.Specification

class MainTest extends Specification {
    def '掛け算のテスト'() {

        expect:
        Main.run(input) == output

        where:
        input | output
        1     | 2

    }

    //模範解答
    def "test #input is #expect"() {
        setup:
        //DBに入れたいものとか

        when:
        def act = Main.run(input)

        then:
        act == expected

        where:
        input | expected
        1     | 2
        2     | 4
    }
}
