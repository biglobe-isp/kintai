package com.naosim.dddwork.service

import com.naosim.dddwork.api.form.KintaiRegistInputForm
import com.naosim.dddwork.domain.KintaiRegistInput
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification

@ContextConfiguration(locations = ["classpath:context.xml"])
class KintaiRegistServiceSpec extends Specification {

    @Autowired
    KintaiRegistService kintaiRegistService

    def "正常データでエラーが発生しないことを確認する"() {
        setup:
        // TODO: data.csvを削除したほうがいいかな

        when:
        String[] args = ["input", "20180102", "0900", "1830"]

        KintaiRegistInputForm inputKintaiForm = new KintaiRegistInputForm(args)
        KintaiRegistInput kintaiRegistInput = inputKintaiForm.getValueObject()

        this.kintaiRegistService.registKintaiOfOneDay(kintaiRegistInput)

        then:
        true
    }
}