package com.naosim.dddwork.kintai_management.api

import com.naosim.dddwork.kintai_management.api.total.KintaiManagementTotalApi
import com.naosim.dddwork.kintai_management.service.total.KintaiManagementTotalServiceInput
import com.naosim.dddwork.kintai_management.service.total.KintaiManagementTotalService
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.web.WebAppConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext
import spock.lang.Specification
import spock.lang.Unroll

/**
 * 勤怠管理集計Apiテスト
 */
@Unroll
@WebAppConfiguration
@ContextConfiguration(locations = "classpath:context-api-test.xml")
@ActiveProfiles(["local", "mvc-test"])
class 勤怠管理集計ApiTest extends Specification {

    @Autowired
    KintaiManagementTotalApi kintaiManagementTotalApi

    @Autowired
    KintaiManagementTotalService kintaiManagementTotalServiceMock

//    @Autowired
//    private WebApplicationContext wac
//    private MockMvc mockMvc

    def setup() {
//        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build()
    }

    def cleanup() {
        Mockito.reset(kintaiManagementTotalServiceMock)
    }

    def "正常_#testCase"() {
        setup:
        Mockito.doNothing().when(kintaiManagementTotalServiceMock).kintaiManagementTotal(Mockito.notNull(KintaiManagementTotalServiceInput.class))

        expect:
//        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post(URI)
//                .param("totalYearMonthForm", "201804")
//        )

            String[] args = [totalYearMonth]
            kintaiManagementTotalApi.main(args)

        where:
        //@formatter:off
        testCase/*	*/| totalYearMonth
        "OK"/*		*/| "201804"
        //@formatter:on
    }

    def "異常_#testCase"() {
        setup:
        Mockito.doNothing().when(kintaiManagementTotalServiceMock).kintaiManagementTotal(Mockito.notNull(KintaiManagementTotalServiceInput.class))

        expect:
        String[] args = []
        kintaiManagementTotalApi.main(args)

        where:
        //@formatter:off
        testCase/*		*/| totalYearMonth
        "引数不足"/*		*/| "201804"
        //@formatter:on
    }
}
