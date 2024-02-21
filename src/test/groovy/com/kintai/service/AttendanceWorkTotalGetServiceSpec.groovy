package com.kintai.service

import com.kintai.datasource.entity.Attendance
import com.kintai.datasource.entity.WorkTotal
import com.kintai.datasource.enums.DayOfWeekKintai
import com.kintai.datasource.value.EndTime
import com.kintai.datasource.value.OverWorkMinutes
import com.kintai.datasource.value.StartTime
import com.kintai.datasource.value.TotalMonth
import com.kintai.datasource.value.WorkDate
import com.kintai.datasource.value.WorkMinutes
import com.kintai.datasource.value.WorkTime
import com.kintai.domain.repository.IAttendanceGetRepository
import com.kintai.domain.service.ITotalDomainService
import com.kintai.service.dto.request.RequestAttendanceGetDto
import com.kintai.service.dto.response.ResponseAttendanceTotalGetDto
import com.kintai.service.usecase.impl.AttendanceTotalGetService
import org.springframework.util.StringUtils
import spock.lang.Specification
import spock.lang.Unroll

import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalDateTime

class AttendanceWorkTotalGetServiceSpec extends Specification {
    @Unroll
    def "正常系(#description)"() {
        setup:
        IAttendanceGetRepository mockIAttendanceGetRepository = Mock()
        List<Attendance> mockAttendanceList = Arrays.asList(makeAttendance(mockAttendanceData1), makeAttendance(mockAttendanceData2), makeAttendance(mockAttendanceData3))
        mockIAttendanceGetRepository.get() >> mockAttendanceList

        ITotalDomainService mockITotalDomainService = Mock()
        // Arrays.asListはaddできないので、ここは普通にインスタンスを生成する
        List<WorkTotal> mockTotalList = new ArrayList<>()
        mockTotalList.add(makeTotal(expectTotalData1))
        if(StringUtils.hasText(expectTotalData2)) {
            mockTotalList.add(makeTotal(expectTotalData2))
        }
        mockITotalDomainService.getMonthlyTotalList(mockAttendanceList) >> mockTotalList
        ResponseAttendanceTotalGetDto expectResponse = new ResponseAttendanceTotalGetDto(mockTotalList, "正常に勤怠データを取得されました。")

        AttendanceTotalGetService service = new AttendanceTotalGetService(mockIAttendanceGetRepository, mockITotalDomainService)

        when:
        ResponseAttendanceTotalGetDto actual = service.get()

        then:
        expectResponse.toString() == actual.toString()

        where: "テストデータ※mockAttendanceDataはカンマ区切りで記載すること。データは右記の通り(勤務月,勤務日,始業時刻,終業時刻)"
        mockAttendanceData1 | mockAttendanceData2 | mockAttendanceData3 || expectTotalData1 || expectTotalData2 || description
        "202401,20240101,0900,1800" | "202401,20240102,0900,2100" | "202401,20240103,0900,2000" || "202401,2000,180" || "" || "取得件数1件"
        "202401,20240101,0900,1800" | "202401,20240102,0900,2100" | "202402,20240201,0900,2100" || "202401,1280,120" || "202402,800,120" | "取得件数複数"
    }

    def "パスワード正常系(#description)"() {
        setup:
        IAttendanceGetRepository mockIAttendanceGetRepository = Mock()
        List<Attendance> mockAttendanceList = Arrays.asList(makeAttendance(mockAttendanceData1), makeAttendance(mockAttendanceData2), makeAttendance(mockAttendanceData3))
        mockIAttendanceGetRepository.get() >> mockAttendanceList

        ITotalDomainService mockITotalDomainService = Mock()
        // Arrays.asListはaddできないので、ここは普通にインスタンスを生成する
        List<WorkTotal> mockTotalList = new ArrayList<>()
        mockTotalList.add(makeTotal(expectTotalData1))
        mockITotalDomainService.getMonthlyTotalList(mockAttendanceList) >> mockTotalList
        ResponseAttendanceTotalGetDto expectResponse = new ResponseAttendanceTotalGetDto(mockTotalList, "正常に勤怠データを取得されました。")
        DayOfWeekKintai testDayOfWeekKintai = getDayOfWeekKintai()
        RequestAttendanceGetDto requestAttendanceGetDto = new RequestAttendanceGetDto(testDayOfWeekKintai.getDayOfName())

        AttendanceTotalGetService service = new AttendanceTotalGetService(mockIAttendanceGetRepository, mockITotalDomainService)

        when:
        ResponseAttendanceTotalGetDto actual = service.get(requestAttendanceGetDto)

        then:
        expectResponse.toString() == actual.toString()

        where: "テストデータ※mockAttendanceDataはカンマ区切りで記載すること。データは右記の通り(勤務月,勤務日,始業時刻,終業時刻)"
        mockAttendanceData1 | mockAttendanceData2 | mockAttendanceData3 || expectTotalData1 || description
        "202401,20240101,0900,1800" | "202401,20240102,0900,2100" | "202401,20240103,0900,2000" || "202401,2000,180" || "取得件数1件"
    }

    def "異常系"() {
        setup:
        IAttendanceGetRepository mockIAttendanceGetRepository = Mock()
        mockIAttendanceGetRepository.get() >> { throw new Exception("error") }

        ITotalDomainService mockITotalDomainService = Mock()

        ResponseAttendanceTotalGetDto expectResponse = new ResponseAttendanceTotalGetDto(new ArrayList<WorkTotal>(), "error")

        AttendanceTotalGetService service = new AttendanceTotalGetService(mockIAttendanceGetRepository, mockITotalDomainService)

        when:
        ResponseAttendanceTotalGetDto actual = service.get()

        then:
        // データ取得に失敗するため、本モックのメソッドは一度も呼ばれていないことを検証
        0 * mockITotalDomainService.getMonthlyTotalList(_)
        expectResponse.toString() == actual.toString()
    }

    def "パスワード異常系"() {
        setup:
        IAttendanceGetRepository mockIAttendanceGetRepository = Mock()
        ITotalDomainService mockITotalDomainService = Mock()
        RequestAttendanceGetDto requestAttendanceGetDto = new RequestAttendanceGetDto("Sunday")
        ResponseAttendanceTotalGetDto expectResponse = new ResponseAttendanceTotalGetDto(new ArrayList<WorkTotal>(), "パスワードが一致しません。")

        AttendanceTotalGetService service = new AttendanceTotalGetService(mockIAttendanceGetRepository, mockITotalDomainService)

        when:
        ResponseAttendanceTotalGetDto actual = service.get(requestAttendanceGetDto)

        then:
        // データ取得に失敗するため、本モックのメソッドは一度も呼ばれていないことを検証
        0 * mockITotalDomainService.getMonthlyTotalList(_)
        expectResponse.toString() == actual.toString()
    }

    String[] parseStringArray(String data) {
        data.split(",")
    }

    Attendance makeAttendance(String data) {
        String[] dataArray = parseStringArray(data)
        TotalMonth totalMonth = new TotalMonth(dataArray[0])
        WorkDate workDate = new WorkDate(dataArray[1])
        WorkTime workTime = new WorkTime(new StartTime(dataArray[2]), new EndTime(dataArray[3]))
        WorkMinutes workMinutes = new WorkMinutes(workTime)
        OverWorkMinutes overWorkMinutes = new OverWorkMinutes(workMinutes)
        new Attendance(totalMonth,workDate,workTime, workMinutes, overWorkMinutes, LocalDateTime.now())
    }

    WorkTotal makeTotal(String data) {
        String[] dataArray = parseStringArray(data)
        TotalMonth totalMonth = new TotalMonth(dataArray[0])
        WorkMinutes workMinutes = new WorkMinutes(Integer.parseInt(dataArray[1]))
        OverWorkMinutes overWorkMinutes = new OverWorkMinutes(Integer.parseInt(dataArray[2]))
        new WorkTotal(totalMonth,workMinutes,overWorkMinutes)
    }

    DayOfWeekKintai getDayOfWeekKintai() {
        DayOfWeek todayDayOfWeek = LocalDate.now().getDayOfWeek()
        DayOfWeekKintai.valueOf(String.valueOf(todayDayOfWeek))
    }
}
