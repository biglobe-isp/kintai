package com.naosim.dddwork.service

import com.naosim.dddwork.api.form.WorkTimeInputForm

import com.naosim.dddwork.api.form.WorkTimeTotalForm
import com.naosim.dddwork.domain.workdateandtime.WorkTimeInputRepository
import com.naosim.dddwork.domain.worktotal.WorkTimeTotalRepository
import spock.lang.Specification

import java.time.LocalDateTime

class WorkTimeServiceSpec extends Specification {

    def 勤務時間入力処理_正常パターン_入力() {
        setup:

        def workTimeInputRepository = Mock(WorkTimeInputRepository)

        def servie = new WorkTimeService()

        when:
        String[] args = ["input", "20170101", "0900", "1800"]
        def workTimeInputForm = new WorkTimeInputForm(args[1], args[2], args[3], LocalDateTime.now().toString())
        def result = servie.workTimeInput(workTimeInputForm.getValueObject())

        then:
        File dataFile = new File("data.csv")
        dataFile.exists()
    }

    def 勤務時間入力処理_正常パターン_合計() {
        setup:
        def workTimeTotalRepository = Mock(WorkTimeTotalRepository)

        def servie = new WorkTimeService()

        when:
        String[] args = ["total", "201701"]
        def workTimeTotalForm = new WorkTimeTotalForm(args[1])
        def workTimeTotal = servie.workTimeTotal(workTimeTotalForm.getValueObject())

        println(">>>> " + workTimeTotal.getTotalNormalWorkTimeYearAndMonth().getValue())
        then:
        assert 8 == workTimeTotal.getTotalNormalWorkTimeYearAndMonth().getValue() / 60 + workTimeTotal.getTotalNormalWorkTimeYearAndMonth().getValue() % 60
        assert 0 == workTimeTotal.getTotalOverWorkTimeYearAndMonth().getValue() / 60 + workTimeTotal.getTotalOverWorkTimeYearAndMonth().getValue()
    }

    def cleanupSpec() {
        //データファイルクリア
        File dataFile = new File("data.csv")
        dataFile.delete()
    }
}
