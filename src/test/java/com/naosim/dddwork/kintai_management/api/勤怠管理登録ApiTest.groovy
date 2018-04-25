package com.naosim.dddwork.kintai_management.api

import com.naosim.dddwork.kintai_management.api.input.KintaiManagementRegistrationApi
import com.naosim.dddwork.kintai_management.service.input.KintaiManagementRegistrationInput
import com.naosim.dddwork.kintai_management.service.input.KintaiManagementRegistrationService
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
 * 勤怠管理登録Apiテスト
 */
@Unroll
@WebAppConfiguration
@ContextConfiguration(locations = "classpath:context-api-test.xml")
@ActiveProfiles(["local", "mvc-test"])
class 勤怠管理登録ApiTest extends Specification {

//    private static final String URI = KintaiManagementRegistrationApi.getURI()

    @Autowired
    KintaiManagementRegistrationApi kintaiManagementRegistrationApi

    @Autowired
    KintaiManagementRegistrationService kintaiManagementRegistrationServiceMock

    @Autowired
    private WebApplicationContext wac
    private MockMvc mockMvc

    def setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build()
    }

    def cleanup() {
        Mockito.reset(kintaiManagementRegistrationServiceMock)
    }

    def "正常_#testCase"() {
        setup:
        Mockito.doNothing().when(kintaiManagementRegistrationServiceMock).kintaiManagementRegistration(Mockito.notNull(KintaiManagementRegistrationInput.class))

        expect:
//        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post(URI)
//                .param("registrationDateForm", "20180411")
//                .param("workingStartTimeForm", "0900")
//                .param("workingEndTimeForm", "1800")
//        )

            String[] args = [registrationDate, workingStartTime, workingEndTime]
            kintaiManagementRegistrationApi.main(args)

        where:
        //@formatter:off
        testCase/*	*/| registrationDate/*	*/| workingStartTime/*	*/| workingEndTime/*	*/
        "OK"/*		*/| "20180102a"/*		*/| "0900"/*			*/| "1830"/*			*/
        //@formatter:on

    }

    def "異常_#testCase"() {
        setup:
        Mockito.doNothing().when(kintaiManagementRegistrationServiceMock).kintaiManagementRegistration(Mockito.notNull(KintaiManagementRegistrationInput.class))

        expect:
        String[] args = [registrationDate, workingStartTime]
        kintaiManagementRegistrationApi.main(args)

        where:
        //@formatter:off
        testCase/*		*/| registrationDate/*	*/| workingStartTime/*	*/
        "引数不足"/*		*/| "20180102"/*		*/| "0900"/*			*/
        //@formatter:on

    }

}
