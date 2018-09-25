package com.naosim.dddwork.api.attendance

import com.naosim.dddwork.service.attendance.AttendanceInputService
import com.naosim.dddwork.service.attendance.AttendanceTotalService
import jp.co.biglobe.lib.publication.date.CurrentLocalDateTimeCreator
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.web.WebAppConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext
import spock.lang.Specification
import spock.lang.Unroll

/**
 * 勤怠管理APIテスト
 */
@Unroll
@WebAppConfiguration
@ContextConfiguration(locations = "classpath:context-api-test.xml")
class AttendanceManagementApiTest extends Specification {

    @Autowired
    private WebApplicationContext wac

    private MockMvc mockMvc

    @Autowired
    private AttendanceManagementApi attendanceManagementApi

//    private AttendanceInputService attendanceInputService = Mock(AttendanceInputService)
//
//    private AttendanceTotalService attendanceTotalService = Mock(AttendanceTotalService)

    def setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build()
    }

    def cleanup() {
        CurrentLocalDateTimeCreator.setSystemClock()
    }

    def "正常_#testCase"() {
        setup:
        String[] args = params

        when:
        attendanceManagementApi.main(args)

        then:
        true
        //TODO 呼び出し回数を評価
//        inputCallCounts * attendanceInputService.input(args)
//        totalCallCounts * attendanceTotalService.refer(args)

        where:
        //@formatter:off
        testCase/*	*/| params                                                  | inputCallCounts | totalCallCounts
//        "INPUT"/*	*/| ["input", "-date:20180901", "-start:0900", "-end:1800"] | 1               | 0
        "INPUT"/*	*/| ["input", "20180901", "0900", "1800"]                   | 1               | 0
        "TOTAL"/*	*/| ["total", "201809"]                                     | 0               | 1
        //@formatter:on
    }
}
