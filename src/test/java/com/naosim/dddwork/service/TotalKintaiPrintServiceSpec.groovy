package com.naosim.dddwork.service

import com.naosim.dddwork.api.form.TotalKintaiPrintInputForm
import com.naosim.dddwork.domain.TotalKintaiPrintInput
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification

@ContextConfiguration(locations = ["classpath:context.xml"])
class TotalKintaiPrintServiceSpec extends Specification {

    @Autowired
    TotalKintaiPrintService totalKintaiPrintService

    def "正常データでエラーが発生しないことを確認する"() {
        setup:
        // TODO: data.csvを削除したほうがいいかな

        when:
        String[] args = ["total", "201801"]

        TotalKintaiPrintInputForm totalKintaiPrintInputForm = new TotalKintaiPrintInputForm(args)
        TotalKintaiPrintInput totalKintaiPrintInput = totalKintaiPrintInputForm.getValueObject()

        this.totalKintaiPrintService.printTargetMonth(totalKintaiPrintInput)

        then:
        true
    }
}