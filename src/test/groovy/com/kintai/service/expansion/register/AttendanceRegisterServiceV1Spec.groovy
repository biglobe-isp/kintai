package com.kintai.service.expansion.register

import com.kintai.datasource.enums.DayOfWeekKintai
import com.kintai.domain.factory.IAttendanceFactory
import com.kintai.domain.repository.IAttendanceSaveRepository
import com.kintai.service.dto.request.RequestAttendanceRegisterDto
import com.kintai.service.dto.response.ResponseAttendanceRegisterDto
import com.kintai.service.usecase.impl.expansion.register.AttendanceRegisterServiceV1
import spock.lang.Specification

import java.time.DayOfWeek
import java.time.LocalDate

class AttendanceRegisterServiceV1Spec extends Specification {
    def "パスワード正常系(#description)"() {
        setup:
        DayOfWeekKintai testDayOfWeekKintai = getDayOfWeekKintai()
        RequestAttendanceRegisterDto dto = new RequestAttendanceRegisterDto(testWorkDate, testStartTime, testEndTime, testDayOfWeekKintai.getDayOfName())
        IAttendanceSaveRepository mockIAttendanceRepository = Mock()
        IAttendanceFactory mockIAttendanceFactory = Mock()
        AttendanceRegisterServiceV1 service = new AttendanceRegisterServiceV1(mockIAttendanceRepository, mockIAttendanceFactory)

        when:
        ResponseAttendanceRegisterDto actual =  service.register(dto)

        then:
        actual.getWorkDate() == expectWorkDate
        actual.getStartTime() == expectStartTime
        actual.getEndTime() == expectEndTime
        actual.getResultMessage() == "正常に勤怠が登録されました。"

        and:
        1 * mockIAttendanceRepository.save(_)

        where:
        testWorkDate | testStartTime | testEndTime || expectWorkDate || expectStartTime || expectEndTime || description
        "20240101" | "0900" | "1800" || "20240101" || "0900" || "1800" || "修正前引数"
        "-date:20240101" | "-start:09_00" | "-end:18_00" || "-date:20240101" || "-start:09_00" || "-end:18_00" || "修正後引数"
    }

    def "パスワード異常系"() {
        setup:
        RequestAttendanceRegisterDto dto = new RequestAttendanceRegisterDto("20240101", "0900", "1800", "test")
        IAttendanceSaveRepository mockIAttendanceRepository = Mock()
        IAttendanceFactory mockIAttendanceFactory = Mock()
        AttendanceRegisterServiceV1 service = new AttendanceRegisterServiceV1(mockIAttendanceRepository, mockIAttendanceFactory)

        when:
        ResponseAttendanceRegisterDto actual =  service.register(dto)

        then:
        actual.getWorkDate() == "20240101"
        actual.getStartTime() == "0900"
        actual.getEndTime() == "1800"
        actual.getResultMessage() == "パスワードが一致しません。"
    }

    DayOfWeekKintai getDayOfWeekKintai() {
        DayOfWeek todayDayOfWeek = LocalDate.now().getDayOfWeek()
        DayOfWeekKintai.valueOf(String.valueOf(todayDayOfWeek))
    }

}
