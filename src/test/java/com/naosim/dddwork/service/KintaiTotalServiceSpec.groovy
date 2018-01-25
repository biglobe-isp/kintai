package com.naosim.dddwork.service

import com.naosim.dddwork.api.form.KintaiTotalPrintInputForm
import com.naosim.dddwork.domain.kintai.KintaiTotalPrintTargetYearMonth
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification

@ContextConfiguration(locations = ["classpath:context.xml"])
class KintaiTotalServiceSpec extends Specification {

    @Autowired
    KintaiTotalPrintService kintaiTotalPrintService

    def "正常データでエラーが発生しないことを確認する"() {
        setup:

        when:
        String[] args = ["total", "201801"]

        KintaiTotalPrintInputForm kintaiTotalPrintInputForm = new KintaiTotalPrintInputForm(args)
        KintaiTotalPrintTargetYearMonth kintaiTotalPrintTargetYearMonth = kintaiTotalPrintInputForm.getValueObject()

        this.kintaiTotalPrintService.printTargetMonth(kintaiTotalPrintTargetYearMonth)

        then:
        true
    }
}