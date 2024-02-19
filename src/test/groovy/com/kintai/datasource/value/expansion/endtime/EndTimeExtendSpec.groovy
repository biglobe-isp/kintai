package com.kintai.datasource.value.expansion.endtime

import com.kintai.exception.ValidatorException
import spock.lang.Specification
import spock.lang.Unroll

class EndTimeExtendSpec extends Specification {
    def "正常系(#descriptions)"() {
        when:
        EndTimeExtend actualEndTimeExtend = new EndTimeExtend(testEndTime)

        then:
        expectEndTime == actualEndTimeExtend.getEndTime()

        where:
        testEndTime || expectEndTime || description
        "-end:18_00" || "1800" || "名称あり"
        "1800" || "1800" || "名称なし"
        "-end:1800" || "1800" || "アンスコなし"
        "18_00" || "1800" || "引数名なし"
    }

    @Unroll
    def "異常系(#description)"() {
        when:
        new EndTimeExtend(errorEndTime)

        then:
        ValidatorException e = thrown()
        e.getMessage() == expectedErrorMessage

        where:
        errorEndTime || expectedErrorMessage || description
        null | "終業時刻は必須です。" || "入力がnull"
        "" | "終業時刻は必須です。" || "入力が空文字"
        " " | "終業時刻は必須です。" || "入力が半角スペースのみ"
        "　" | "終業時刻は必須です。" || "入力が全角スペースのみ"
        "-end:19" || "終業時刻の形式はHHmmでなければなりません。" || "形式エラー(HH)"
        "-end:18_0" || "終業時刻の形式はHHmmでなければなりません。" || "桁数3桁"
        "-end:018_00" || "終業時刻の形式はHHmmでなければなりません。" || "桁数5桁"
        "-end:１８＿００" || "終業時刻の形式はHHmmでなければなりません。" || "全角数字"
        "-end:HH_mm" || "終業時刻の形式はHHmmでなければなりません。" || "半角英字"
        "-end:あい" || "終業時刻の形式はHHmmでなければなりません。" || "全角文字"
    }
}
