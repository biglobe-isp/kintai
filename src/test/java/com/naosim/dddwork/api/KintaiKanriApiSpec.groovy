package com.naosim.dddwork.api

import com.naosim.dddwork.service.KintaiRegisterService
import com.naosim.dddwork.service.KintaiTotalPrintService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification

@ContextConfiguration(locations = ["classpath:context.xml"])
class KintaiKanriApiSpec extends Specification {

    @Autowired
    KintaiRegisterService kintaiRegistService

    @Autowired
    KintaiTotalPrintService totalKintaiPrintService

    def "inputでエラーが発生しないことを確認する"() {
        setup:
        // TODO: data.csvを削除したほうがいいかな
        KintaiKanriApi kintaiKanriApi = new KintaiKanriApi(kintaiRegistService, totalKintaiPrintService)

        when:
        String[] args = ["input", "20180102", "0900", "1830"]
        kintaiKanriApi.execute(args)

        then:
        true
    }

    def "totalでエラーが発生しないことを確認する"() {
        setup:
        KintaiKanriApi kintaiKanriApi = new KintaiKanriApi(kintaiRegistService, totalKintaiPrintService)

        when:
        String[] args = ["total", "201801"]
        kintaiKanriApi.execute(args)

        then:
        true
    }

}