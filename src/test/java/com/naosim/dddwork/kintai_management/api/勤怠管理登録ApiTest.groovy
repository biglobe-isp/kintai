package com.naosim.dddwork.kintai_management.api

import com.naosim.dddwork.kintai_management.api.input.KintaiManagementRegistrationApi
import com.naosim.dddwork.kintai_management.domain.word.HolidayKind
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
        testCase/*				*/| registrationDate/*		*/| workingStartTime/*		*/| workingEndTime/*	*/
//        "OK"/*		*/| "20180102"/*		*/| "0900"/*			*/| "1830"/*			*/
        "OK"/*					*/| "-date:20180102"/*		*/| "-start:0900"/*			*/| "-end:1830"/*			*/
        "OK(順序違いでのも良い)"/*	*/| "-start:0900"/*			*/| "-end:1830"/*			*/| "-date:20180102"/*		*/
        //@formatter:on

    }

    def "正常（休暇あり）_#testCase"() {
        setup:
        Mockito.doNothing().when(kintaiManagementRegistrationServiceMock).kintaiManagementRegistration(Mockito.notNull(KintaiManagementRegistrationInput.class))

        expect:
        String[] args = [registrationDate, workingStartTime, workingEndTime, holidayKind]
        kintaiManagementRegistrationApi.main(args)

        where:
        //@formatter:off
        testCase/*		*/| registrationDate/*		*/| workingStartTime/*		*/| workingEndTime/*	*/| holidayKind
        "OK（全休）"/*	*/| "-date:20180102"/*		*/| null/*					*/| null/*				*/| "v"
        "OK（AM半休）"/*	*/| "-date:20180102"/*		*/| null/*					*/| "-end:1830"/*		*/| "am"
        "OK（PM半休）"/*	*/| "-date:20180102"/*		*/| "-start:0900"/*			*/| null/*				*/| "pm"
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

    def "異常2_#testCase"() {
        setup:
        Mockito.doNothing().when(kintaiManagementRegistrationServiceMock).kintaiManagementRegistration(Mockito.notNull(KintaiManagementRegistrationInput.class))

        expect:
        String[] args = [registrationDate, workingStartTime, workingEndTime]
        kintaiManagementRegistrationApi.main(args)

        where:
        //@formatter:off
        testCase/*	*/| registrationDate/*		*/| workingStartTime/*	*/| workingEndTime/*	*/
        "引数不正"/*	*/| "-date:20180102"/*		*/| "-XXstart:0900"/*	*/| "-end:1830"/*		*/
        //@formatter:on

    }

}
