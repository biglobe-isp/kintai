package com.naosim.dddwork.kintai_management.service


import com.naosim.dddwork.kintai_management.domain.word.RegistrationDate
import com.naosim.dddwork.kintai_management.domain.word.WorkingEndTime
import com.naosim.dddwork.kintai_management.domain.word.WorkingStartTime
import com.naosim.dddwork.kintai_management.service.input.KintaiManagementRegistrationInput
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
                new KintaiManagementRegistrationInput(
                        new RegistrationDate("20180413"),
                        new WorkingStartTime("0900"),
                        new WorkingEndTime("2200")
                )
        )

        then:
        String test = "test"

    }
}
