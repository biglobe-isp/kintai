package com.naosim.dddwork.datasource

import com.naosim.dddwork.domain.WorkTimeRepository
import spock.lang.Specification

class WorkTimeRepositoryInputSpec extends Specification {
    private WorkTimeRepository workTimeRepository;

    def setup() {


    }

    def "勤怠入力のテストを行う_入力パラメータ不正"() {

        setup:
        workTimeRepository = new WorkTimeRepositoryInput()

        when:
        String[] args = ["input", "20170101", "0900"]
        workTimeRepository.workTimeCalExec(args)

        then:
        def ex = thrown(RuntimeException)
        File dataFile = new File("data.csv")
        !dataFile.exists()
    }

    def "勤怠入力のテストを行う_正常パターン"() {

        setup:
        workTimeRepository = new WorkTimeRepositoryInput()

        when:
        String[] args = ["input", "20170101", "0900", "1800"]
        workTimeRepository.workTimeCalExec(args)

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
