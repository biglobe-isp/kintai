package com.kintai.datasource.value.expansion.starttime

import com.kintai.exception.ValidatorException
import spock.lang.Specification
import spock.lang.Unroll

class StartTimeExtendSpec extends Specification {
    def "正常系(#description)"() {
        when:
        StartTimeExtend actualStartTimeExtend = new StartTimeExtend(testStartTime)

        then:
        expectStartTime == actualStartTimeExtend.getStartTime()

        where:
        testStartTime || expectStartTime || description
        "-start:09_00" || "0900" || "名称あり"
        "0900" || "0900" || "名称なし"
        "-start:0900" || "0900" || "アンスコなし"
        "09_00" || "0900" || "引数名なし"
    }

    @Unroll
    def "異常系(#description)"() {
        when:
        new StartTimeExtend(errorStartTime)

        then:
        ValidatorException e = thrown()
        e.getMessage() == expectedErrorMessage

        where:
        errorStartTime || expectedErrorMessage || description
        null | "開始時刻は必須です。" || "入力がnull"
        "" | "開始時刻は必須です。" || "入力が空文字"
        " " | "開始時刻は必須です。" || "入力が半角スペースのみ"
        "　" | "開始時刻は必須です。" || "入力が全角スペースのみ"
        "-start:19" || "開始時刻の形式はHHmmでなければなりません。" || "形式エラー(HH)"
        "-start:18_0" || "開始時刻の形式はHHmmでなければなりません。" || "桁数3桁"
        "-start:018_00" || "開始時刻の形式はHHmmでなければなりません。" || "桁数5桁"
        "-start:１８＿００" || "開始時刻の形式はHHmmでなければなりません。" || "全角数字"
        "-start:HH_mm" || "開始時刻の形式はHHmmでなければなりません。" || "半角英字"
        "-start:あい" || "開始時刻の形式はHHmmでなければなりません。" || "全角文字"
    }
}
