package com.naosim.dddwork.service

import com.naosim.dddwork.api.form.KintaiRegistInputForm
import com.naosim.dddwork.domain.kintai.register.WorkStartAndEndTimeOfOneDay
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification

@ContextConfiguration(locations = ["classpath:context.xml"])
class KintaiRegisterServiceSpec extends Specification {

    @Autowired
    KintaiRegisterService kintaiRegistService

    def "正常データでエラーが発生しないことを確認する"() {
        setup:
        // TODO: data.csvを削除したほうがいいかな

        when:
        String[] args = ["input", "20180102", "0900", "1830"]

        KintaiRegistInputForm inputKintaiForm = new KintaiRegistInputForm(args)
        WorkStartAndEndTimeOfOneDay kintaiRegistInput = inputKintaiForm.getValueObject()

        this.kintaiRegistService.registerKintaiOfOneDay(kintaiRegistInput)

        then:
        true
    }
}