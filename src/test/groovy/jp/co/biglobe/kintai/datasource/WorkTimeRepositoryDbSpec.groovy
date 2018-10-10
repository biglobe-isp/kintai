package jp.co.biglobe.kintai.datasource

import jp.co.biglobe.kintai.domain.EndWorkTime
import jp.co.biglobe.kintai.domain.NowTime
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
    private def nowTime = new NowTime("201810130900")

    def "inputの処理テスト"(){
        setup:
        def workTime = new WorkTime(workDate,startTime,endTime,0,0,nowTime)

        when:
        workTimeRepositoryDb.input(workTime)

        then:
        true
    }
}
