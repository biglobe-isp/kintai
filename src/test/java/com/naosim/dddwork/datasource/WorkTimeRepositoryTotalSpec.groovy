package com.naosim.dddwork.datasource

import com.naosim.dddwork.domain.WorkTimeRepository
import com.naosim.dddwork.domain.WorkTimeTotal
import spock.lang.Specification

class WorkTimeRepositoryTotalSpec extends Specification {

    def setupSpec() {
        WorkTimeRepository workTimeRepository = new WorkTimeRepositoryInput()
        String[] args = ["input", "20170101", "0900", "1800"]
        workTimeRepository.doExecute(args)

    }

    def "集計のテストを行う_異常パターン"() {

        setup:
        WorkTimeRepository workTimeRepository = new WorkTimeRepositoryTotal()

        when:
        String[] args = ["total"]
        workTimeRepository.doExecute(args)

        then:
        def ex = thrown(RuntimeException)
    }

    def "集計のテストを行う_正常パターン"() {

        setup:
        WorkTimeRepository inputWorkTimeRepository = new WorkTimeRepositoryInput()
        String[] inputPram = ["input", "20170102", "0900", "1800"]
        inputWorkTimeRepository.doExecute(inputPram);

        WorkTimeRepository workTimeRepository = new WorkTimeRepositoryTotal()

        when:

        String[] args = ["total","201701"]
        WorkTimeTotal workTimeTotal =  workTimeRepository.doExecute(args)

        then:
        assert workTimeTotal != null
        assert 16 == workTimeTotal.getTotalWorkMinutes() /60
        assert 0 == workTimeTotal.getTotalOverWorkMinutes()
    }

    def cleanupSpec() {
        //データファイルクリア
        File dataFile = new File("data.csv")
        dataFile.delete()
    }
}
