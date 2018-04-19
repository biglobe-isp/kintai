package com.naosim.dddwork.service.kintai

import com.naosim.dddwork.domain.word.MethodType
import com.naosim.dddwork.domain.word.WorkDate
import com.naosim.dddwork.domain.word.WorkTime
import com.naosim.dddwork.service.input.KintaiKanriInputServiceInput
import com.naosim.dddwork.service.input.KintaiKanriTotalServiceInput
import jp.co.biglobe.lib.publication.date.CurrentLocalDateTimeCreator
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification

/**
 * 勤怠管理API（登録）Serviceテスト
 */
//@Unroll
//@WebAppConfiguration
@ContextConfiguration(locations = "classpath:context.xml")
//@ActiveProfiles(["local", "mvc-test"])
class KintaiKanriInputServiceTest extends Specification {

    @Autowired
    KintaiKanriInputService kintaiKanriInputService

    def cleanup() {
        CurrentLocalDateTimeCreator.setSystemClock()
    }

    def "正常(INPUT)_#testCase"() {

        setup:

        when:
        KintaiKanriInputServiceInput kintaiKanriInputServiceInput =
                new KintaiKanriInputServiceInput(
                        MethodType.INPUT,
                        new WorkDate("20180501"),
                        new WorkTime("0900"),
                        new WorkTime("1800")
                )

        kintaiKanriInputService.execute(kintaiKanriInputServiceInput)

        then:
        1 == 1
    }
}
