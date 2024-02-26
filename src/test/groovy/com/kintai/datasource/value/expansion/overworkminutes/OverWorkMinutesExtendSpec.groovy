package com.kintai.datasource.value.expansion.overworkminutes

import com.kintai.datasource.value.EndTime
import com.kintai.datasource.value.StartTime
import com.kintai.datasource.value.WorkDate
import com.kintai.datasource.value.WorkTime
import com.kintai.datasource.value.expansion.workminutes.WorkMinutesExtend
import spock.lang.Specification
import spock.lang.Unroll

/**
 * 拡張した残業時間のテストクラス。
 * 休憩時間が追加後の残業時間の計算方法が正しいことをテストします。
 */
class OverWorkMinutesExtendSpec extends Specification {
    /**
     * 休憩時間追加後の残業時間の計算が正しいことをテスト。
     * 休憩時間は「2024/03/15」以降に追加されるため、前日、当日、後日で残業時間を確認。1日の労働基準時間が変更になるだけで残業時間は変わらないことを確認
     * 実装が修正されていることを確認するために労働時間が変更されていることも確認
     */
    @Unroll
    def "正常系(#description)"() {
        when:
        WorkDate testWorkDate = new WorkDate(workDateValue)
        WorkTime testWorkTime = new WorkTime(new StartTime(startTimeValue), new EndTime(endTimeValue))
        WorkMinutesExtend testWorkMinutesExtend = new WorkMinutesExtend(testWorkTime, testWorkDate)
        OverWorkMinutesExtend actualOverWorkMinutesExtend = new OverWorkMinutesExtend(testWorkMinutesExtend, testWorkDate)

        then:
        testWorkMinutesExtend.getWorkMinutes() == expectWorkMinutes
        actualOverWorkMinutesExtend.getOverWorkMinutes() == expectOverWorkMinute

        where:
        workDateValue | startTimeValue | endTimeValue || expectWorkMinutes || expectOverWorkMinute || description
        "20240314" | "0900" | "2000" || 540 || 60 || "休憩時間増える前日"
        "20240315" | "0900" | "2000" || 480 || 60 || "休憩時間増える当日"
        "20240316" | "0900" | "2000" || 480 || 60 || "休憩時間増える後日"
    }
}
