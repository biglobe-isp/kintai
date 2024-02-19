package com.kintai.datasource.value

import com.kintai.exception.ValidatorException
import spock.lang.Specification
import spock.lang.Unroll

class WorkTimeSpec extends Specification {
    @Unroll
    def "正常系(#description)"() {
        setup:
        WorkTime actualWorkTime = new WorkTime(startTime, endTime)

        expect:
        expectStartTime == actualWorkTime.getStartTime().getStartTime()
        expectEndTime == actualWorkTime.getEndTime().getEndTime()

        where:
        startTime | endTime || expectStartTime || expectEndTime || description
        "0900" | "1800" || "0900" || "1800" || "修正前引数"
        "-start:09_00" | "-end:18_00" || "0900" || "1800" || "修正後引数"
    }

    @Unroll
    def "バリデーションエラー(#description)"() {
        when:
        new WorkTime(startTime, endTime)

        then:
        ValidatorException e = thrown()
        e.getMessage() == expected

        where:
        startTime | endTime || expected ||  description
        null | "1200" || "開始時刻は必須です。" || "開始時刻がnull"
        "" | "1200" || "開始時刻は必須です。" || "開始時刻が空白"
        " " | "1200" || "開始時刻は必須です。" || "開始時刻が半角スペースのみ"
        "　" | "1200" || "開始時刻は必須です。" || "開始時刻が全角スペースのみ"
        "0900" | null || "終業時刻は必須です。" || "終業時刻がnull"
        "0900" | "" || "終業時刻は必須です。" || "終業時刻が空白"
        "0900" | " " || "終業時刻は必須です。" || "終業時刻が半角スペースのみ"
        "0900" | "　" || "終業時刻は必須です。" || "終業時刻が全角スペースのみ"
        "0900" | "19" || "開始時刻または終業時刻の形式はHHmmでなければなりません。" || "形式エラー(HH)"
        "090" | "1900" || "開始時刻または終業時刻の形式はHHmmでなければなりません。" || "桁数3桁"
        "09000" | "1900" || "開始時刻または終業時刻の形式はHHmmでなければなりません。" || "桁数5桁"
        "０９００" | "1900" || "開始時刻または終業時刻の形式はHHmmでなければなりません。" || "全角数字"
        "0900" | "HHmm" || "開始時刻または終業時刻の形式はHHmmでなければなりません。" || "半角英字"
        "0900" | "あい" || "開始時刻または終業時刻の形式はHHmmでなければなりません。" || "全角文字"
        "0900" | "0800" || "開始時刻が終業時刻より、後に設定されています。" || "始業時刻のほうが後に設定されている(時)"
        "0902" | "0901" || "開始時刻が終業時刻より、後に設定されています。" || "始業時刻のほうが後に設定されている(分)"
        "0900" | "0900" || "開始時刻が終業時刻より、後に設定されています。" || "始業時刻と終業時刻が同じ"
    }
}
