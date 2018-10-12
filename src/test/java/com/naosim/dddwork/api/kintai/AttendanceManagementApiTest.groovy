package com.naosim.dddwork.api.kintai

import com.naosim.dddwork.api.AttendanceManagementApi
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

    def setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build()
    }

    def cleanup() {
        CurrentLocalDateTimeCreator.setSystemClock()
    }

    def "#testCase"() {
        setup:
        String[] args = params

        when:
        attendanceManagementApi.main(args)

        then:
        true

        where:
        //@formatter:off
        testCase/*	*/| params
//        "INPUT"/*	*/| ["input","20181101", "0900", "1800"]
//        "INPUT"/*	*/| ["input","20181101", "0900", "2000"]
//        "INPUT"/*	*/| ["input","20181102", "0900", "1800"]
//        "INPUT"/*	*/| ["input","20181103", "0900", "1800"]
//        "INPUT"/*	*/| ["input","20181104", "0900", "1800"]
//        "INPUT"/*	*/| ["input","20181105", "0900", "1800"]
//        "INPUT"/*	*/| ["input","20181106", "0900", "1800"]
//        "INPUT"/*	*/| ["input","20181107", "0900", "1800"]
//        "INPUT"/*	*/| ["input","20181108", "0900", "1900"]
//        "INPUT"/*	*/| ["input","20181109", "0900", "1800"]
//        "INPUT"/*	*/| ["input","20181110", "0900", "2100"]
//        "INPUT"/*	*/| ["input","20181115", "0900", "2100"]
//        "INPUT"/*	*/| ["input","20181116", "0900", "1800"]
        "INPUT"/*	*/| ["input","20181116", "0900", "2000"]
        "INPUT"/*	*/| ["input","20181117", "0900", "1800"]
        "TOTAL"/*	*/| ["total", "201811"]
        //@formatter:on
    }
}
