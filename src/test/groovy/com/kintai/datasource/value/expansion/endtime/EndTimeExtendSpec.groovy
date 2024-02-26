package com.kintai.datasource.value.expansion.endtime

import com.kintai.exception.ValidatorException
import spock.lang.Specification
import spock.lang.Unroll

/**
 * 拡張した終業時刻のテストクラス。
 * 入力変更後の終業時刻の正常系と異常系のテストを実施します。
 */
class EndTimeExtendSpec extends Specification {
    /**
     * 入力方法が変更したケースと変更前のケースの両方でテスト実施。
     * 「-end:」と「_」の値がパースされた値がクラス内に格納されていることを確認します。
     */
    @Unroll
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

    /**
     * 必須チェックと形式チェックを行います。
     * 形式チェックは入力方法変更後の形式でチェック時に異常を検知することを確認します。
     */
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
