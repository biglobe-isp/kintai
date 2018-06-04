package com.naosim.dddwork.kintai_management.api

import com.naosim.dddwork.kintai_management.api.regist.KintaiManagementRegistApi
import com.naosim.dddwork.kintai_management.service.regist.KintaiManagementRegistServiceInput
import com.naosim.dddwork.kintai_management.service.regist.KintaiManagementRegistService
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.web.WebAppConfiguration
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
    KintaiManagementRegistApi kintaiManagementRegistApi

    @Autowired
    KintaiManagementRegistService kintaiManagementRegistServiceMock

//    @Autowired
//    private WebApplicationContext wac
//    private MockMvc mockMvc

    def setup() {
//        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build()
    }

    def cleanup() {
        Mockito.reset(kintaiManagementRegistServiceMock)
    }

    def "正常_#testCase"() {
        setup:
        Mockito.doNothing().when(kintaiManagementRegistServiceMock).kintaiManagementRegist(Mockito.notNull(KintaiManagementRegistServiceInput.class))

        expect:
//        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post(URI)
//                .param("registrationDateForm", "20180411")
//                .param("workingStartTimeForm", "0900")
//                .param("workingEndTimeForm", "1800")
//        )
        String[] args = [registrationDate, workingStartTime, workingEndTime]
        kintaiManagementRegistApi.main(args)

        where:
        //@formatter:off
        testCase/*			*/| registrationDate/*		*/| workingStartTime/*		*/| workingEndTime/*		*/
//        "OK"/*		*/| "20180102"/*		*/| "0900"/*			*/| "1830"/*			*/
        "OK"/*				*/| "-date:20180102"/*		*/| "-start:0900"/*			*/| "-end:1830"/*			*/
        "OK(順序違い)"/*		*/| "-start:0900"/*			*/| "-end:1830"/*			*/| "-date:20180102"/*		*/
        //@formatter:on
    }

    def "正常（休暇あり）_#testCase"() {
        setup:
        Mockito.doNothing().when(kintaiManagementRegistServiceMock).kintaiManagementRegist(Mockito.notNull(KintaiManagementRegistServiceInput.class))

        expect:
        String[] args = [registrationDate, workingStartTime, workingEndTime, holidayKind]
        kintaiManagementRegistApi.main(args)

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
        Mockito.doNothing().when(kintaiManagementRegistServiceMock).kintaiManagementRegist(Mockito.notNull(KintaiManagementRegistServiceInput.class))

        expect:
        String[] args = [registrationDate, workingStartTime]
        kintaiManagementRegistApi.main(args)

        where:
        //@formatter:off
        testCase/*		*/| registrationDate/*	*/| workingStartTime/*	*/
        "引数不足"/*		*/| "20180102"/*		*/| "0900"/*			*/
        //@formatter:on
    }

    def "異常2_#testCase"() {
        setup:
        Mockito.doNothing().when(kintaiManagementRegistServiceMock).kintaiManagementRegist(Mockito.notNull(KintaiManagementRegistServiceInput.class))

        expect:
        String[] args = [registrationDate, workingStartTime, workingEndTime]
        kintaiManagementRegistApi.main(args)

        where:
        //@formatter:off
        testCase/*	*/| registrationDate/*		*/| workingStartTime/*	*/| workingEndTime/*	*/
        "引数不正"/*	*/| "-date:20180102"/*		*/| "-XXstart:0900"/*	*/| "-end:1830"/*		*/
        //@formatter:on
    }
}
