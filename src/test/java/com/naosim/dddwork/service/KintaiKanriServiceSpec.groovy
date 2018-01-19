package com.naosim.dddwork.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification

@ContextConfiguration(locations = ["classpath:context.xml"])
class KintaiKanriServiceSpec extends Specification {

    @Autowired
    KintaiKanriService kintaiKanriService

    def "inputでエラーが発生しないことを確認する"() {
        setup:
        // TODO: data.csvを削除したほうがいいかな

        when:
        String[] args = ["input", "20180102", "0900", "1830"]
        kintaiKanriService.execute(args)

        then:
        true
    }

    def "totalでエラーが発生しないことを確認する"() {
        setup:
        when:
        String[] args = ["total", "201801"]
        kintaiKanriService.execute(args)

        then:
        true
    }
}