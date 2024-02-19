package com.kintai.domain.service

import com.kintai.datasource.entity.Attendance
import com.kintai.datasource.entity.Total
import com.kintai.datasource.value.OverWorkMinutes
import com.kintai.datasource.value.TotalMonth
import com.kintai.datasource.value.WorkDate
import com.kintai.datasource.value.WorkMinutes
import com.kintai.datasource.value.WorkTime
import com.kintai.domain.service.impl.TotalDomainService
import spock.lang.Specification

import java.time.LocalDateTime

class TotalDomainServiceSpec extends Specification {
    def "正常系"() {
        setup:
        List<Attendance> testAttendance = new ArrayList<>()
        for (String whereData : Arrays.asList(testData1, testData2, testData3)) {
            String[] arrayTestData = parseStringArray(whereData)
            TotalMonth totalMonth = new TotalMonth(arrayTestData[0])
            WorkDate workDate = new WorkDate(arrayTestData[1])
            WorkTime workTime = new WorkTime(arrayTestData[2], arrayTestData[3])
            WorkMinutes workMinutes = new WorkMinutes(workTime)
            OverWorkMinutes overWorkMinutes = new OverWorkMinutes(workMinutes)
            testAttendance.add(new Attendance(totalMonth,workDate,workTime, workMinutes, overWorkMinutes, LocalDateTime.now()))
        }
        TotalDomainService totalDomainService = new TotalDomainService();

        when:
        List<Attendance> actualList = totalDomainService.getMonthlyTotalList(testAttendance)

        then:
        expectDataCount == actualList.size()
        List<String[]> expectArrayData = Arrays.asList(parseStringArray(expectData1), parseStringArray(expectData2))
        for(int i = 0; i < actualList.size(); i++) {
            Total expectTotal = actualList.get(i)
            String[] expectArray = expectArrayData[i]

            expectArray[0] == expectTotal.getTotalMonth().getTotalMonth()
            Integer.parseInt(expectArray[1]) == expectTotal.getWorkMinutes().getWorkMinutes()
            Integer.parseInt(expectArray[2]) == expectTotal.getOverWorkMinutes().getOverWorkMinutes()
        }

        where: "テストデータ※testDataはカンマ区切りで記載すること。データは右記の通り(勤務月,勤務日,始業時刻,終業時刻)"
        testData1 | testData2 | testData3 || expectDataCount || expectData1 || expectData2
        "202401,20240101,0900,1800" | "202401,20240102,0900,2100" | "202401,20240103,0900,2000" || 1 || "202401,2000,180" || ""
        "202401,20240101,0900,1800" | "202401,20240102,0900,2100" | "202402,20240201,0900,2100" || 2 || "202401,1280,120" || "202402,800,120"
    }

    String[] parseStringArray(String data) {
        data.split(",")
    }
}
