package com.kintai.datasource.value

import com.kintai.exception.ValidatorException
import spock.lang.Specification
import spock.lang.Unroll

class WorkTimeSpec extends Specification {
    @Unroll
    def "正常系"() {
        setup:
        WorkTime actualWorkTime = new WorkTime(new StartTime("0900"), new EndTime("1800"))

        expect:
        "0900" == actualWorkTime.getStartTime().getStartTime()
        "1800" == actualWorkTime.getEndTime().getEndTime()
    }

    @Unroll
    def "バリデーションエラー(#description)"() {
        when:
        new WorkTime(new StartTime(startTime), new EndTime(endTime))

        then:
        ValidatorException e = thrown()
        e.getMessage() == expected

        where:
        startTime | endTime || expected ||  description
        "0900" | "0800" || "開始時刻が終業時刻より、後に設定されています。" || "始業時刻のほうが後に設定されている(時)"
        "0902" | "0901" || "開始時刻が終業時刻より、後に設定されています。" || "始業時刻のほうが後に設定されている(分)"
        "0900" | "0900" || "開始時刻が終業時刻より、後に設定されています。" || "始業時刻と終業時刻が同じ"
    }
}
