package com.naosim.dddwork.service

import com.naosim.dddwork.api.form.KintaiTotalPrintInputForm
import com.naosim.dddwork.domain.KintaiTotalPrintInput
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification

@ContextConfiguration(locations = ["classpath:context.xml"])
class KintaiTotalPrintServiceSpec extends Specification {

    @Autowired
    KintaiTotalPrintService totalKintaiPrintService

    def "正常データでエラーが発生しないことを確認する"() {
        setup:
        // TODO: data.csvを削除したほうがいいかな

        when:
        String[] args = ["total", "201801"]

        KintaiTotalPrintInputForm totalKintaiPrintInputForm = new KintaiTotalPrintInputForm(args)
        KintaiTotalPrintInput totalKintaiPrintInput = totalKintaiPrintInputForm.getValueObject()

        this.totalKintaiPrintService.printTargetMonth(totalKintaiPrintInput)

        then:
        true
    }
}