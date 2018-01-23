package com.naosim.dddwork.datasource

import com.naosim.dddwork.api.form.WorkTimeInputForm
import com.naosim.dddwork.domain.WorkTimeRepository
import spock.lang.Specification

import java.time.LocalDateTime

class WorkTimeInputRepositoryFileSpec extends Specification {
    private WorkTimeRepository workTimeRepository;

    def "勤怠入力のテストを行う_正常パターン"() {

        setup:
        workTimeRepository = new WorkTimeInputRepositoryFile()

        when:
        String[] args = ["input", "20170101", "0900", "1800"]
        WorkTimeInputForm workTimeInputForm = new WorkTimeInputForm(args[1], args[2], args[3], LocalDateTime.now().toString())
        workTimeRepository.doWorktimeTaskExecute(workTimeInputForm.getValueObject())

        then:
        File dataFile = new File("data.csv")
        dataFile.exists()
    }

    def cleanupSpec() {
        //データファイルクリア
        File dataFile = new File("data.csv")
        dataFile.delete()
    }
}
