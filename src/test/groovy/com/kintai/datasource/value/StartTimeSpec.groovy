package com.kintai.datasource.value

import com.kintai.exception.ValidatorException
import spock.lang.Specification
import spock.lang.Unroll

class StartTimeSpec extends Specification {
    def "正常系"() {
        when:
        StartTime startTime = new StartTime("0900")

        then:
        "0900" == startTime.getStartTime()
    }

    @Unroll
    def "異常系(#description)"() {
        when:
        new StartTime(errorStartTime)

        then:
        ValidatorException e = thrown()
        e.getMessage() == expectedErrorMessage

        where:
        errorStartTime || expectedErrorMessage || description
        null | "開始時刻は必須です。" || "入力がnull"
        "" | "開始時刻は必須です。" || "入力が空文字"
        " " | "開始時刻は必須です。" || "入力が半角スペースのみ"
        "　" | "開始時刻は必須です。" || "入力が全角スペースのみ"
        "19" || "開始時刻の形式はHHmmでなければなりません。" || "形式エラー(HH)"
        "090" || "開始時刻の形式はHHmmでなければなりません。" || "桁数3桁"
        "09000" || "開始時刻の形式はHHmmでなければなりません。" || "桁数5桁"
        "０９００" || "開始時刻の形式はHHmmでなければなりません。" || "全角数字"
        "HHmm" || "開始時刻の形式はHHmmでなければなりません。" || "半角英字"
        "あい" || "開始時刻の形式はHHmmでなければなりません。" || "全角文字"
    }
}
