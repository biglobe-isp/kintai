package com.naosim.dddwork.datasource

import com.naosim.dddwork.api.form.WorkTimeInputForm
import com.naosim.dddwork.domain.workdateandtime.WorkTimeInputRepository
import com.naosim.dddwork.api.form.WorkTimeTotalForm
import com.naosim.dddwork.domain.worktotal.WorkTimeTotalCalculation
import com.naosim.dddwork.domain.worktotal.WorkTimeTotalRepository
import spock.lang.Specification

import java.time.LocalDateTime

class WorkTimeTotalRepositoryFileSpec extends Specification {
    WorkTimeInputRepository workTimeInputRepository;

    def setup() {
        workTimeInputRepository = new WorkTimeInputRepositoryFile()
    }

    def "集計のテストを行う_正常パターン_残業なし"() {

        setup:
        (20170101..20170131).each {
            String[] args = ["input", "${it}", "0900", "1800"]
            WorkTimeInputForm workTimeInputForm = new WorkTimeInputForm(args[1], args[2], args[3], LocalDateTime.now().toString())
            workTimeInputRepository.doWorktimeTaskExecute(workTimeInputForm.getValueObject())
        }

        when:
        String[] args = ["total", "201701"]
        WorkTimeTotalRepository workTimeTotalRepository = new WorkTimeTotalRepositoryFile()
        WorkTimeTotalForm workTimeTotalForm = new WorkTimeTotalForm(args[1])
        WorkTimeTotalCalculation workTimeTotalCalculation = workTimeTotalRepository.doWorktimeTaskExecute(workTimeTotalForm.getValueObject())

        then:
        assert workTimeTotalCalculation != null
    }

    def "集計のテストを行う_正常パターン_残業あり"() {

        setup:
        (20170101..20170131).each {
            String[] args = ["input", "${it}", "0900", "2000"]
            WorkTimeInputForm workTimeInputForm = new WorkTimeInputForm(args[1], args[2], args[3], LocalDateTime.now().toString())
            workTimeInputRepository.doWorktimeTaskExecute(workTimeInputForm.getValueObject())
        }

        when:

        String[] args = ["total", "201701"]
        WorkTimeTotalRepository workTimeTotalRepository = new WorkTimeTotalRepositoryFile()
        WorkTimeTotalForm workTimeTotalForm = new WorkTimeTotalForm(args[1])
        WorkTimeTotalCalculation workTimeTotalCalculation = workTimeTotalRepository.doWorktimeTaskExecute(workTimeTotalForm.getValueObject())

        then:
        assert workTimeTotalCalculation != null
    }

    def cleanup() {
        //データファイルクリア
        File dataFile = new File("data.csv")
        dataFile.delete()
    }
}
