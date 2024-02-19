package com.kintai.datasource.value.expansion.workdate

import com.kintai.exception.ValidatorException
import spock.lang.Specification
import spock.lang.Unroll

class WorkDateExtendSpec extends Specification {
    @Unroll
    def "正常系(#description)"() {
        when:
        WorkDateExtend actualWorkDateExtend = new WorkDateExtend(testWorkDateExtend)

        then:
        actualWorkDateExtend.getWorkDate() == expectWorkDateExtend

        where:
        testWorkDateExtend || expectWorkDateExtend || description
        "-date:20170101" || "20170101" || "名称あり"
        "20170101" || "20170101" || "名称なし"
    }

    @Unroll
    def "異常系(#description)"() {
        when:
        new WorkDateExtend(assertWorkDate)

        then:
        ValidatorException e = thrown()
        e.getMessage() == expected

        where:
        assertWorkDate | expected || description
        null | "勤務日は必須です。" || "入力がnull"
        "" | "勤務日は必須です。" || "入力が空文字"
        " " | "勤務日は必須です。" || "入力が半角スペースのみ"
        "　" | "勤務日は必須です。" || "入力が全角スペースのみ"
        "-date:2024" | "勤務日の形式はyyyyMMddでなければなりません。" || "入力がyyyy"
        "-date:202401" | "勤務日の形式はyyyyMMddでなければなりません。" || "入力がyyyyMM"
        "-date:2024010" | "勤務日の形式はyyyyMMddでなければなりません。" || "入力値が7桁"
        "-date:20241232" | "勤務日の形式はyyyyMMddでなければなりません。" || "存在しない日付"
        "-date:20250229" | "勤務日の形式はyyyyMMddでなければなりません。" || "存在しない日付(うるう年以外)"
        "-date:202401301" | "勤務日の形式はyyyyMMddでなければなりません。" || "入力値が9桁"
        "-date:uuuuMMdd" | "勤務日の形式はyyyyMMddでなければなりません。" || "入力が半角英字"
        "-date:あいうえ" | "勤務日の形式はyyyyMMddでなければなりません。" || "入力が全角文字"
        "-date:２０２４１２３１" | "勤務日の形式はyyyyMMddでなければなりません。" || "入力が全角数字"
    }
}
