package com.naosim.dddwork.kintai_management.service

import com.naosim.dddwork.kintai_management.domain.word.TotalYearMonth
import com.naosim.dddwork.kintai_management.service.total.KintaiManagementTotalInput
import com.naosim.dddwork.kintai_management.service.total.KintaiManagementTotalService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.web.WebAppConfiguration
import spock.lang.Specification
import spock.lang.Unroll

/**
 * 勤怠管理集計サービステスト
 */
@Unroll
@WebAppConfiguration
@ContextConfiguration(locations = "classpath:context.xml")
@ActiveProfiles(["local", "mvc-test"])
class 勤怠管理集計ServiceTest extends Specification {

    @Autowired
    private KintaiManagementTotalService kintaiManagementTotalService;

    def setup() {
    }

    def cleanup() {
    }

    def "正常_#testCase"() {
        setup:

        when:
        /** 登録Service実行 */
        kintaiManagementTotalService.kintaiManagementTotal(
                new KintaiManagementTotalInput(
                        new TotalYearMonth("201804")
                )
        )

        then:
        String test = "test"

    }
}
