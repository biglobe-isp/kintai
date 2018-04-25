package com.naosim.dddwork.service.kintai

import com.naosim.dddwork.domain.word.MethodType
import com.naosim.dddwork.service.input.KintaiKanriTotalServiceInput
import jp.co.biglobe.lib.publication.date.CurrentLocalDateTimeCreator
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification

/**
 * 勤怠管理API（集計）Serviceテスト
 */
//@Unroll
//@WebAppConfiguration
@ContextConfiguration(locations = "classpath:context.xml")
//@ActiveProfiles(["local", "mvc-test"])
class KintaiKanriTotalServiceTest extends Specification {

    @Autowired
    KintaiKanriTotalService kintaiKanriTotalService

    def cleanup() {
        CurrentLocalDateTimeCreator.setSystemClock()
    }

    def "正常(TOTAL)_#testCase"() {

        setup:

        when:
        KintaiKanriTotalServiceInput kintaiKanriTotalServiceInput =
                new KintaiKanriTotalServiceInput(
                        MethodType.TOTAL
                )

        kintaiKanriTotalService.execute(kintaiKanriTotalServiceInput)

        then:
        1 == 1
    }
}
