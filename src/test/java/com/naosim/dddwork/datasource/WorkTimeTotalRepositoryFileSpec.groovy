package com.naosim.dddwork.datasource

import com.naosim.dddwork.api.form.WorkTimeInputForm
import com.naosim.dddwork.domain.WorkTimeRepository
import com.naosim.dddwork.domain.WorkTimeTotal
import com.naosim.dddwork.api.form.WorkTimeTotalForm
import spock.lang.Specification

import java.time.LocalDateTime

class WorkTimeTotalRepositoryFileSpec extends Specification {

    def "集計のテストを行う_正常パターン_残業なし"() {

        setup:
        WorkTimeRepository workTimeRepository = new WorkTimeInputRepositoryFile()

        (20170101..20170131).each {
            String[] args = ["input", "${it}", "0900", "1800"]
            WorkTimeInputForm workTimeInputForm = new WorkTimeInputForm(args[1], args[2], args[3], LocalDateTime.now().toString())
            workTimeRepository.doWorktimeTaskExecute(workTimeInputForm.getValueObject())
        }

        when:

        String[] args = ["total", "201701"]
        workTimeRepository = new WorkTimeTotalRepositoryFile()
        WorkTimeTotalForm workTimeTotalForm = new WorkTimeTotalForm(args[1])
        WorkTimeTotal workTimeTotal = workTimeRepository.doWorktimeTaskExecute(workTimeTotalForm.getValueObject())

        then:
        assert workTimeTotal != null
        assert 248 == workTimeTotal.getTotalWorkMinutes() / 60 + workTimeTotal.getTotalWorkMinutes() % 60
        assert 0 == workTimeTotal.getTotalOverWorkMinutes() / 60 + workTimeTotal.getTotalOverWorkMinutes() % 60
    }

    def "集計のテストを行う_正常パターン_残業あり"() {

        setup:
        WorkTimeRepository workTimeRepository = new WorkTimeInputRepositoryFile()

        (20170101..20170131).each {
            String[] args = ["input", "${it}", "0900", "2000"]
            WorkTimeInputForm workTimeInputForm = new WorkTimeInputForm(args[1], args[2], args[3], LocalDateTime.now().toString())
            workTimeRepository.doWorktimeTaskExecute(workTimeInputForm.getValueObject())
        }

        when:

        String[] args = ["total", "201701"]
        workTimeRepository = new WorkTimeTotalRepositoryFile()
        WorkTimeTotalForm workTimeTotalForm = new WorkTimeTotalForm(args[1])
        WorkTimeTotal workTimeTotal = workTimeRepository.doWorktimeTaskExecute(workTimeTotalForm.getValueObject())

        then:
        assert workTimeTotal != null
        assert 279 == workTimeTotal.getTotalWorkMinutes() / 60 + workTimeTotal.getTotalWorkMinutes() % 60
        assert 31 == workTimeTotal.getTotalOverWorkMinutes() / 60 + workTimeTotal.getTotalOverWorkMinutes() % 60
    }

    def cleanup() {
        //データファイルクリア
        File dataFile = new File("data.csv")
        dataFile.delete()
    }
}
