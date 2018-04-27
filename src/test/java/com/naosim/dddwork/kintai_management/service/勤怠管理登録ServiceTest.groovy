package com.naosim.dddwork.kintai_management.service

import com.naosim.dddwork.kintai_management.domain.word.HolidayKind
import com.naosim.dddwork.kintai_management.domain.word.RegistrationDate
import com.naosim.dddwork.kintai_management.domain.word.WorkingEndTime
import com.naosim.dddwork.kintai_management.domain.word.WorkingStartTime
import com.naosim.dddwork.kintai_management.service.input.KintaiManagementRegistrationServiceInput
import com.naosim.dddwork.kintai_management.service.input.KintaiManagementRegistrationService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.web.WebAppConfiguration
import spock.lang.Specification
import spock.lang.Unroll

/**
 * 勤怠管理登録サービステスト
 */
@Unroll
@WebAppConfiguration
@ContextConfiguration(locations = "classpath:context.xml")
@ActiveProfiles(["local", "mvc-test"])
class 勤怠管理登録ServiceTest extends Specification {

    @Autowired
    private KintaiManagementRegistrationService kintaiManagementRegistrationService;

    def setup() {
    }

    def cleanup() {
    }

    def "正常_#testCase"() {
        setup:

        when:
        /** 登録Service実行 */
        kintaiManagementRegistrationService.kintaiManagementRegistration(
                new KintaiManagementRegistrationServiceInput(
                        new RegistrationDate(registrationDate),
                        new WorkingStartTime(workingStartTime),
                        new WorkingEndTime(workingEndTime),
                        new HolidayKind(holidayKind)
                )
        )

        then:
        String test = "test"

        where:
        //@formatter:off
        testCase/*			*/| registrationDate/*	*/| workingStartTime/*	*/| workingEndTime/*	*/| holidayKind
        "通常（残業なし）"/*	*/| "20180501"/*		*/| "0900"/*			*/| "2000"/*			*/| null
        "通常（残業あり）"/*	*/| "20180501"/*		*/| "0900"/*			*/| "2100"/*			*/| null
        "全休"/*				*/| "20180501"/*		*/| null/*				*/| null/*				*/| "v"
        "am半休"/*			*/| "20180501"/*		*/| null/*				*/| "2000"/*			*/| "am"
        "pm半休"/*			*/| "20180501"/*		*/| "0900"/*			*/| null/*				*/| "pm"
        //@formatter:on

    }
}
