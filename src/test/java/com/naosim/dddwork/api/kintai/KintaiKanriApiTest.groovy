package com.naosim.dddwork.api.kintai

import com.naosim.dddwork.service.input.KintaiKanriInputServiceInput
import com.naosim.dddwork.service.input.KintaiKanriTotalServiceInput
import com.naosim.dddwork.service.kintai.KintaiKanriInputService
import com.naosim.dddwork.service.kintai.KintaiKanriTotalService
import jp.co.biglobe.lib.publication.date.CurrentLocalDateTimeCreator
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.web.WebAppConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext
import spock.lang.Specification
import spock.lang.Unroll

import javax.validation.ValidationException

/**
 * 勤怠管理APIテスト
 */
@Unroll
@WebAppConfiguration
@ContextConfiguration(locations = "classpath:context-api-test.xml")
class KintaiKanriApiTest extends Specification {

    @Autowired
    KintaiKanriInputService kintaiKanriInputServiceMock

    @Autowired
    KintaiKanriTotalService kintaiKanriTotalServiceMock

    @Autowired
    private WebApplicationContext wac

    private MockMvc mockMvc

    def setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build()
    }

    def cleanup() {
        CurrentLocalDateTimeCreator.setSystemClock()
        Mockito.reset(kintaiKanriInputServiceMock)
        Mockito.reset(kintaiKanriTotalServiceMock)
    }

    def "正常_#testCase"() {
        setup:
        Mockito.doNothing().when(kintaiKanriInputServiceMock).execute(Mockito.notNull(KintaiKanriInputServiceInput.class))
        Mockito.doNothing().when(kintaiKanriTotalServiceMock).execute(Mockito.notNull(KintaiKanriTotalServiceInput.class))

        when:
        String[] args = argsSets
        KintaiKanriApi kintaiKanriApi = new KintaiKanriApi(kintaiKanriInputServiceMock, kintaiKanriTotalServiceMock);
        kintaiKanriApi.main(args)

        then:
        1 == 1

        where:
        //@formatter:off
        testCase/*	*/| argsSets
        "INPUT"/*	*/| ["input", "20180410", "0900", "1800"]
        "INPUT"/*	*/| ["input", "20180411", "0900", "2044"]
        "INPUT"/*	*/| ["input", "20180412", "0800", "1800"]
        "INPUT"/*	*/| ["input", "20180413", "0000", "2359"]
        "INPUT"/*	*/| ["input", "20180414", "0916", "1956"]
        "INPUT"/*	*/| ["input", "20180416", "0900", "2000"]
        "INPUT"/*	*/| ["input", "20180417", "0900", "2000"]
        "INPUT"/*	*/| ["input", "20180418", "0900", "2000"]
        "INPUT"/*	*/| ["input", "20180419", "0900", "2000"]
        "INPUT"/*	*/| ["input", "20180420", "0900", "2000"]
        "INPUT"/*	*/| ["input", "20180423", "0900", "2000"]
        "INPUT"/*	*/| ["input", "20180424", "0900", "2000"]
        "INPUT"/*	*/| ["input", "20180425", "0900", "2000"]
        "INPUT"/*	*/| ["input", "20180426", "0900", "2000"]
        "INPUT"/*	*/| ["input", "20180427", "0900", "2000"]
        "INPUT"/*	*/| ["input", "20180507", "0900", "2000"]
        "INPUT"/*	*/| ["input", "20180508", "0900", "2000"]
        "INPUT"/*	*/| ["input", "20180509", "0900", "2000"]
        "INPUT"/*	*/| ["input", "20180510", "0900", "2000"]
        "INPUT"/*	*/| ["input", "20180511", "0900", "2000"]
        "INPUT"/*	*/| ["input", "20180512", "0900", "2000"]
        "INPUT"/*	*/| ["input", "20180513", "0900", "2000"]
        "INPUT"/*	*/| ["input", "20180514", "0900", "2000"]
        "INPUT"/*	*/| ["input", "20180515", "0900", "2000"]
        "INPUT"/*	*/| ["input", "20180516", "0900", "2000"]
        "INPUT"/*	*/| ["input", "20180517", "0900", "2000"]
        "TOTAL"/*	*/| ["total"]
        //@formatter:on
    }

    def "異常（IllegalArgumentException）_#testCase"() {
        setup:
        Mockito.doNothing().when(kintaiKanriInputServiceMock).execute(Mockito.notNull(KintaiKanriInputServiceInput.class))
        Mockito.doNothing().when(kintaiKanriTotalServiceMock).execute(Mockito.notNull(KintaiKanriTotalServiceInput.class))

        when:
        String[] args = argsSets
        KintaiKanriApi kintaiKanriApi = new KintaiKanriApi(kintaiKanriInputServiceMock, kintaiKanriTotalServiceMock);
        kintaiKanriApi.main(args)

        then:
        IllegalArgumentException re = thrown()
        re.printStackTrace()

        where:
        //@formatter:off
        testCase/*						*/| argsSets
        "INPUT args数不足"/*				*/| ["input", "20180411"]
        "TOTAL args数過多"/*				*/| ["total", "20180411", "0900", "1800"]
        "INPUT methodTypeに既定値以外"/*	*/| ["a",     "ABCDEFGH", "0900", "1800"]
        "TOTAL methodTypeに既定値以外"/*	*/| ["x"]
        //@formatter:on
    }

    def "異常（ValidationException）_#testCase"() {
        setup:
        Mockito.doNothing().when(kintaiKanriInputServiceMock).execute(Mockito.notNull(KintaiKanriInputServiceInput.class))
        Mockito.doNothing().when(kintaiKanriTotalServiceMock).execute(Mockito.notNull(KintaiKanriTotalServiceInput.class))

        when:
        String[] args = argsSets
        KintaiKanriApi kintaiKanriApi = new KintaiKanriApi(kintaiKanriInputServiceMock, kintaiKanriTotalServiceMock);
        kintaiKanriApi.main(args)

        then:
        ValidationException ve = thrown()
        ve.printStackTrace()

        where:
        //@formatter:off
        testCase/*						*/| argsSets
        "INPUT 対象日付に数値以外"/*		*/| ["input", "ABCDEFGH", "0900", "1800"]
        "INPUT 出勤時刻に数値以外"/*		*/| ["input", "20180411", "A000", "1800"]
        "INPUT 退勤時刻に数値以外"/*		*/| ["input", "20180411", "0900", "B000"]
        //@formatter:on
    }
}
