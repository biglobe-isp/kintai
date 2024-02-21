package com.kintai.datasource.value.expansion.password

import com.kintai.datasource.enums.DayOfWeekKintai
import com.kintai.exception.ValidatorException
import spock.lang.Specification
import spock.lang.Unroll

import java.time.DayOfWeek
import java.time.LocalDate

class CsvPasswordSpec extends Specification {
    Map<Integer, DayOfWeek> map = new HashMap<>();

    def setup() {
        map.put(0, DayOfWeek.MONDAY)
        map.put(1, DayOfWeek.TUESDAY)
        map.put(2, DayOfWeek.WEDNESDAY)
        map.put(3, DayOfWeek.THURSDAY)
        map.put(4, DayOfWeek.FRIDAY)
        map.put(5, DayOfWeek.SATURDAY)
        map.put(6, DayOfWeek.SUNDAY)
    }

    def "正常系"() {
        when:
        DayOfWeekKintai testDayOfWeekKintai = getDayOfWeekKintai()
        CsvPassword actualCsvPassword = new CsvPassword(testDayOfWeekKintai.getDayOfName())

        then:
        actualCsvPassword.getPassword() == testDayOfWeekKintai.getDayOfName()
    }

    def "異常系(#description)"() {
        when:
        new CsvPassword(testPassword)

        then:
        ValidatorException e = thrown()
        e.getMessage() == errorMessage

        where:
        testPassword || errorMessage || description
        "Sunday" || "パスワードが一致しません。" || "パスワード不一致(入力した曜日が違う)"
        "test" || "パスワードが一致しません。" || "パスワード不一致(パスワード違い)"
    }

    DayOfWeekKintai getDayOfWeekKintai() {
        DayOfWeek todayDayOfWeek = LocalDate.now().getDayOfWeek();
        DayOfWeekKintai.valueOf(String.valueOf(todayDayOfWeek));
    }
}
