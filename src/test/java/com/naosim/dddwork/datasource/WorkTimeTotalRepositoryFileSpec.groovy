package com.naosim.dddwork.datasource

import com.naosim.dddwork.domain.WorkTimeRepository
import com.naosim.dddwork.domain.WorkTimeTotal
import spock.lang.Specification

class WorkTimeTotalRepositoryFileSpec extends Specification {

    def setupSpec() {
        WorkTimeRepository workTimeRepository = new WorkTimeInputRepositoryFile()
        //String[] args = ["input", "20170101", "0900", "1800"]

        (20170101..20170131).each {
            String[] args = ["input", "${it}", "0900", "2000"]

            workTimeRepository.doExecute(args)
        }

    }

    def "集計のテストを行う_異常パターン"() {

        setup:
        WorkTimeRepository workTimeRepository = new WorkTimeTotalRepositoryFile()

        when:
        String[] args = ["total"]
        workTimeRepository.doExecute(args)

        then:
        def ex = thrown(RuntimeException)
    }

    def "集計のテストを行う_正常パターン_残業なし"() {

        setup:
        WorkTimeRepository workTimeRepository = new WorkTimeInputRepositoryFile()

        (20170101..20170131).each {
            String[] argsInput = ["input", "${it}", "0900", "1800"]
            workTimeRepository.doExecute(argsInput)
        }

        when:

        String[] args = ["total","201701"]
        workTimeRepository = new WorkTimeTotalRepositoryFile()
        WorkTimeTotal workTimeTotal =  workTimeRepository.doExecute(args)

        then:
        assert workTimeTotal != null
        assert 248 == workTimeTotal.getTotalWorkMinutes() /60 + workTimeTotal.getTotalWorkMinutes() % 60
        assert 0 == workTimeTotal.getTotalOverWorkMinutes() / 60 +  workTimeTotal.getTotalOverWorkMinutes() % 60
    }

    def "集計のテストを行う_正常パターン_残業あり"() {

        setup:
        WorkTimeRepository workTimeRepository = new WorkTimeInputRepositoryFile()

        (20170101..20170131).each {
            String[] argsInput = ["input", "${it}", "0900", "2000"]
            workTimeRepository.doExecute(argsInput)
        }

        when:

        String[] args = ["total","201701"]
        workTimeRepository = new WorkTimeTotalRepositoryFile()
        WorkTimeTotal workTimeTotal =  workTimeRepository.doExecute(args)

        then:
        assert workTimeTotal != null
        assert 279 == workTimeTotal.getTotalWorkMinutes() /60 + workTimeTotal.getTotalWorkMinutes() % 60
        assert 31 == workTimeTotal.getTotalOverWorkMinutes() / 60 +  workTimeTotal.getTotalOverWorkMinutes() % 60
    }

    def cleanup() {
        //データファイルクリア
        File dataFile = new File("data.csv")
        dataFile.delete()
    }
}
