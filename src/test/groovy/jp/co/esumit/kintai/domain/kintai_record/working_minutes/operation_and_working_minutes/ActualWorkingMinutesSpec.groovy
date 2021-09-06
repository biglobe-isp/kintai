package jp.co.esumit.kintai.domain.kintai_record.working_minutes.operation_and_working_minutes

import jp.co.esumit.kintai.domain.kintai_record.time_card.EndTime
import jp.co.esumit.kintai.domain.kintai_record.time_card.StartTime
import jp.co.esumit.kintai.domain.kintai_record.time_card.TimeCard
import spock.lang.Specification

class ActualWorkingMinutesSpec extends Specification {
    def "正常系 #no #label #startTime~#endTime"() {

        when:
        def target =
                ActualWorkingMinutes.create(new TimeCard(new StartTime(startTime), new EndTime(endTime)), new BreakTimes())
        then:
        target == new ActualWorkingMinutes(result)

        where:
        no | startTime | endTime | result       || label
        1  | "0900"    | "1100"  | 2 * 60       || "休憩時間に作業時間が被らない"
        2  | "0900"    | "1200"  | 3 * 60       || "休憩開始時に終了"
        3  | "0900"    | "1230"  | 3 * 60       || "休憩時間中に作業終了"
        4  | "0900"    | "1300"  | 3 * 60       || "休憩終了時に作業終了"
        5  | "0900"    | "1400"  | 4 * 60       || "休憩時間前/後に作業を開始/終了"
        6  | "1215"    | "1245"  | 0            || "休憩時間中に作業を開始/終了"
        7  | "1200"    | "1400"  | 60           || "休憩開始時に作業開始"
        8  | "1215"    | "1400"  | 60           || "休憩時間中に作業開始"
        9  | "1300"    | "1400"  | 60           || "休憩終了時に作業開始"
        10 | "1330"    | "1400"  | 30           || "休憩時間に作業時間が被らない"
        11 | "0900"    | "1800"  | 7 * 60       || "18:00開始の休憩時間を含まない"
        12 | "0900"    | "1830"  | 7 * 60       || "18:00開始の休憩時間を30分含む"
        13 | "0900"    | "1900"  | 7 * 60       || "18:00開始の休憩時間を60分含む"
        14 | "0900"    | "1930"  | 7 * 60 + 30  || "通常"
        15 | "0900"    | "2000"  | 8 * 60       || "通常"
        16 | "0900"    | "2030"  | 8 * 60 + 30  || "通常"
        17 | "0900"    | "2100"  | 9 * 60       || "21:00開始の休憩時間を含まない"
        18 | "0900"    | "2130"  | 9 * 60       || "21:00開始の休憩時間を30分含む"
        19 | "0900"    | "2200"  | 9 * 60       || "21:00開始の休憩時間を60分含む"
        20 | "0900"    | "2230"  | 9 * 60 + 30  || "通常"
        21 | "0900"    | "2300"  | 10 * 60      || "通常"
        22 | "0900"    | "2330"  | 10 * 60 + 30 || "通常"
    }
}
