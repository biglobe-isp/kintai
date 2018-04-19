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
        "INPUT"/*	*/| ["input", "-date:20180410", "-start:0900", "-end:1800"]
        "INPUT"/*	*/| ["input", "-date:20180411", "-start:0900", "-end:2044"]
        "INPUT"/*	*/| ["input", "-date:20180412", "-start:0800", "-end:1800"]
        "INPUT"/*	*/| ["input", "-date:20180413", "-start:0000", "-end:2359"]
        "INPUT"/*	*/| ["input", "-date:20180414", "-start:0916", "-end:1956"]
        "INPUT"/*	*/| ["input", "-date:20180416", "-start:0900", "-end:2000"]
        "INPUT"/*	*/| ["input", "-date:20180417", "-start:0900", "-end:2000"]
        "INPUT"/*	*/| ["input", "-date:20180418", "-start:0900", "-end:2000"]
        "INPUT"/*	*/| ["input", "-date:20180419", "-start:0900", "-end:2000"]
        "INPUT"/*	*/| ["input", "-date:20180420", "-start:0900", "-end:2000"]
        "INPUT"/*	*/| ["input", "-date:20180423", "-start:0900", "-end:2000"]
        "INPUT"/*	*/| ["input", "-date:20180424", "-start:0900", "-end:2000"]
        "INPUT"/*	*/| ["input", "-date:20180425", "-start:0900", "-end:2000"]
        "INPUT"/*	*/| ["input", "-date:20180426", "-start:0900", "-end:2000"]
        "INPUT"/*	*/| ["input", "-date:20180427", "-start:0900", "-end:2000"]
        "INPUT"/*	*/| ["input", "-date:20180507", "-start:0900", "-end:2000"]
        "INPUT"/*	*/| ["input", "-date:20180508", "-start:0900", "-end:2000"]
        "INPUT"/*	*/| ["input", "-date:20180509", "-start:0900", "-end:2000"]
        "INPUT"/*	*/| ["input", "-date:20180510", "-start:0900", "-end:2000"]
        "INPUT"/*	*/| ["input", "-date:20180511", "-start:0900", "-end:2000"]
        "INPUT"/*	*/| ["input", "-date:20180512", "-start:0900", "-end:2000"]
        "INPUT"/*	*/| ["input", "-date:20180513", "-start:0900", "-end:2000"]
        "INPUT"/*	*/| ["input", "-date:20180514", "-start:0900", "-end:2000"]
        "INPUT"/*	*/| ["input", "-date:20180515", "-start:0900", "-end:2000"]
        "INPUT"/*	*/| ["input", "-date:20180516", "-start:0900", "-end:2000"]
        "INPUT"/*	*/| ["input", "-date:20180517", "-start:0900", "-end:2000"]
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
        "INPUT args数不足"/*				*/| ["input", "-date:20180411"]
        "TOTAL args数過多"/*				*/| ["total", "-date:20180411", "-start:0900", "-end:1800"]
        "INPUT methodTypeに既定値以外"/*	*/| ["a",     "-date:ABCDEFGH", "-start:0900", "-end:1800"]
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
        "INPUT 対象日付に数値以外"/*		*/| ["input", "-date:ABCDEFGH", "-start:0900", "-end:1800"]
        "INPUT 出勤時刻に数値以外"/*		*/| ["input", "-date:20180411", "-start:A000", "-end:1800"]
        "INPUT 退勤時刻に数値以外"/*		*/| ["input", "-date:20180411", "-start:0900", "-end:B000"]
        "INPUT 対象日付の書式不正"/*		*/| ["input", "-data:ABCDEFGH", "-start:0900", "-end:1800"]
        "INPUT 出勤時刻の書式不正"/*		*/| ["input", "-date:20180411", "-stort:A000", "-end:1800"]
        "INPUT 退勤時刻の書式不正"/*		*/| ["input", "-date:20180411", "-start:0900", "-emd:B000"]
        //@formatter:on
    }
}
