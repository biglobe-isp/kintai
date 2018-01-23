package com.naosim.dddwork.service

import com.naosim.dddwork.api.form.WorkTimeInputForm
import com.naosim.dddwork.domain.WorkTimeRepository
import com.naosim.dddwork.api.form.WorkTimeTotalForm
import spock.lang.Specification

import java.time.LocalDateTime

class WorkTimeServiceSpec extends Specification {

    def 勤務時間入力処理_正常パターン_入力() {
        setup:

        def workTimeRepository = Mock(WorkTimeRepository)

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
        def workTimeRepository = Mock(WorkTimeRepository)

        def servie = new WorkTimeService()

        when:
        String[] args = ["total", "201701"]
        def workTimeTotalForm = new WorkTimeTotalForm(args[1])
        def workTimeTotal = servie.workTimeTotal(workTimeTotalForm.getValueObject())

        then:
        assert 8 == workTimeTotal.getTotalWorkMinutes() / 60 + workTimeTotal.getTotalWorkMinutes() % 60
        assert 0 == workTimeTotal.getTotalOverWorkMinutes() / 60 + workTimeTotal.getTotalOverWorkMinutes()
    }

    def cleanupSpec() {
        //データファイルクリア
        File dataFile = new File("data.csv")
        dataFile.delete()
    }
}
