package com.kintai.service

import com.kintai.domain.factory.IAttendanceFactory
import com.kintai.domain.repository.IAttendanceSaveRepository
import com.kintai.exception.ValidatorException
import com.kintai.service.dto.request.RequestAttendanceRegisterDto
import com.kintai.service.dto.response.ResponseAttendanceRegisterDto
import com.kintai.service.usecase.impl.AttendanceRegisterService
import spock.lang.Specification

class AttendanceRegisterServiceSpec extends Specification {
    def "正常系(#description)"() {
        setup:
        RequestAttendanceRegisterDto dto = new RequestAttendanceRegisterDto(testWorkDate, testStartTime, testEndTime)
        IAttendanceSaveRepository mockIAttendanceRepository = Mock()
        IAttendanceFactory mockIAttendanceFactory = Mock()
        AttendanceRegisterService service = new AttendanceRegisterService(mockIAttendanceRepository, mockIAttendanceFactory)

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

    def "異常系"() {
        setup:
        RequestAttendanceRegisterDto dto = new RequestAttendanceRegisterDto("202401", "0900", "1800")
        IAttendanceSaveRepository mockIAttendanceRepository = Mock()
        IAttendanceFactory mockIAttendanceFactory = Mock()
        mockIAttendanceFactory.makeAttendance(_) >> { throw new ValidatorException("勤務日の形式はyyyyMMddでなければなりません。") }
        AttendanceRegisterService service = new AttendanceRegisterService(mockIAttendanceRepository, mockIAttendanceFactory)

        when:
        ResponseAttendanceRegisterDto actual =  service.register(dto)

        then:
        actual.getWorkDate() == "202401"
        actual.getStartTime() == "0900"
        actual.getEndTime() == "1800"
        actual.getResultMessage() == "勤務日の形式はyyyyMMddでなければなりません。"
    }

    def "システムエラー"() {
        setup:
        RequestAttendanceRegisterDto dto = new RequestAttendanceRegisterDto("20240101", "0900", "1800")
        IAttendanceSaveRepository mockIAttendanceRepository = Mock()
        IAttendanceFactory mockIAttendanceFactory = Mock()
        mockIAttendanceRepository.save(_) >> { throw new Exception("error") }
        AttendanceRegisterService service = new AttendanceRegisterService(mockIAttendanceRepository, mockIAttendanceFactory)

        when:
        ResponseAttendanceRegisterDto actual =  service.register(dto)

        then:
        actual.getWorkDate() == "20240101"
        actual.getStartTime() == "0900"
        actual.getEndTime() == "1800"
        actual.getResultMessage() == "error"
    }
}
