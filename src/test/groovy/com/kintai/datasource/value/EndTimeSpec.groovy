package com.kintai.datasource.value

import com.kintai.exception.ValidatorException
import spock.lang.Specification
import spock.lang.Unroll

class EndTimeSpec extends Specification {
    def "正常系"() {
        when:
        EndTime endTime = new EndTime("1800")

        then:
        "1800" == endTime.getEndTime()
    }

    @Unroll
    def "異常系(#description)"() {
        when:
        new EndTime(errorEndTime)

        then:
        ValidatorException e = thrown()
        e.getMessage() == expectedErrorMessage

        where:
        errorEndTime || expectedErrorMessage || description
        null | "終業時刻は必須です。" || "入力がnull"
        "" | "終業時刻は必須です。" || "入力が空文字"
        " " | "終業時刻は必須です。" || "入力が半角スペースのみ"
        "　" | "終業時刻は必須です。" || "入力が全角スペースのみ"
        "19" || "終業時刻の形式はHHmmでなければなりません。" || "形式エラー(HH)"
        "180" || "終業時刻の形式はHHmmでなければなりません。" || "桁数3桁"
        "01800" || "終業時刻の形式はHHmmでなければなりません。" || "桁数5桁"
        "１８００" || "終業時刻の形式はHHmmでなければなりません。" || "全角数字"
        "HHmm" || "終業時刻の形式はHHmmでなければなりません。" || "半角英字"
        "あい" || "終業時刻の形式はHHmmでなければなりません。" || "全角文字"
    }
}
