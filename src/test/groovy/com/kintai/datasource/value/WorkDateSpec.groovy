package com.kintai.datasource.value

import com.kintai.exception.ValidatorException
import spock.lang.Specification
import spock.lang.Unroll

class WorkDateSpec extends Specification {
    def "正常系"() {
        when:
        WorkDate workDate = new WorkDate("20240101")

        then: "勤務日がオブジェクト上に存在すること"
        workDate.getWorkDate() == "20240101"

//        "-date:20240101" || "20240101" || "修正後引数"
    }

    @Unroll
    def "バリデーションエラー #description"() {
        when:
        new WorkDate(assertWorkDate)

        then:
        ValidatorException e = thrown()
        e.getMessage() == expected

        where:
        assertWorkDate | expected || description
        null | "勤務日は必須です。" || "入力がnull"
        "" | "勤務日は必須です。" || "入力が空文字"
        " " | "勤務日は必須です。" || "入力が半角スペースのみ"
        "　" | "勤務日は必須です。" || "入力が全角スペースのみ"
        "2024" | "勤務日の形式はyyyyMMddでなければなりません。" || "入力がyyyy"
        "202401" | "勤務日の形式はyyyyMMddでなければなりません。" || "入力がyyyyMM"
        "2024010" | "勤務日の形式はyyyyMMddでなければなりません。" || "入力値が7桁"
        "20241232" | "勤務日の形式はyyyyMMddでなければなりません。" || "存在しない日付"
        "20250229" | "勤務日の形式はyyyyMMddでなければなりません。" || "存在しない日付(うるう年以外)"
        "202401301" | "勤務日の形式はyyyyMMddでなければなりません。" || "入力値が9桁"
        "uuuuMMdd" | "勤務日の形式はyyyyMMddでなければなりません。" || "入力が半角英字"
        "あいうえ" | "勤務日の形式はyyyyMMddでなければなりません。" || "入力が全角文字"
        "２０２４１２３１" | "勤務日の形式はyyyyMMddでなければなりません。" || "入力が全角数字"
    }
}
