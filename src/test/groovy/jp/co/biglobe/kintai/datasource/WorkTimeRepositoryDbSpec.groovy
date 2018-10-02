package jp.co.biglobe.kintai.datasource

import jp.co.biglobe.kintai.domain.EndWorkTime
import jp.co.biglobe.kintai.domain.StartWorkTime
import jp.co.biglobe.kintai.domain.WorkDate
import jp.co.biglobe.kintai.domain.WorkTime
import spock.lang.Specification
import spock.lang.Unroll

@Unroll
class WorkTimeRepositoryDbSpec extends Specification{

    private def workTimeRepositoryDb = new WorkTimeRepositoryDb()

    private def startTime = new StartWorkTime("0900")
    private def endTime = new EndWorkTime("1800")
    private def workDate  = new WorkDate("20181010")

    def "inputの処理テスト"(){
        setup:
        def workTime = new WorkTime()
        workTime.setDate(workDate)
        workTime.setStartTime(startTime)
        workTime.setEndTime(endTime)
        workTime.setNow("now")


        when:
        workTimeRepositoryDb.input(workTime)

        then:
        true
    }

    def "findWorkTimerCardの処理テスト"(){
        setup:

        when:
        def result =workTimeRepositoryDb.findWorkTimeCard("201810")

        then:
        true
    }
}
