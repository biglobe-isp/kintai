package jp.co.biglobe.isp.kintai.api

import com.blogspot.toomuchcoding.spock.subjcollabs.Subject
import jp.co.biglobe.isp.kintai.domain.monthly_accumulated_hour.FixtureMonthlyAccumulatedWorkMinutes
import jp.co.biglobe.isp.kintai.service.monthly_accumulated_hour.MonthlyAccumulatedHourReferenceService
import jp.co.biglobe.isp.kintai.service.workrecord_registration.WorkRecordRegistrationService
import spock.lang.Specification
import spock.lang.Unroll


@Unroll
class ControllerSpec extends Specification{
    private WorkRecordRegistrationService workRecordRegistrationService = Mock()
    private MonthlyAccumulatedHourReferenceService monthlyAccumulatedHourReferenceService = Mock()

    @Subject
    private InputController inputController
    @Subject
    private TotalController totalController

    def "InputController normal args: #label " () {
        setup:
        def args= argsStr.split(" ")

        when:
        inputController.run(args)

        // Mockをつくって、
        then:
        1 * workRecordRegistrationService.register(_)

        where:
        label                       | argsStr
        "openingTime is just time"  | "input 20230101 0900 1800"
    }

    def "InputController irregular args: #label " () {
        setup:
        def args = argsStr.split(" ")

        when:
        inputController.run(args)

        then:
        Exception e = thrown()
        e.getMessage() == expected

        where:
        label                                         | argsStr                     || expected
        "openingTime is after closingTime"            | "input 20230101 1800 0900"  || "勤務開始時刻は勤務終了時刻より早い時間に設定してください."
    }
    def "TotalController normal args: #label " () {
        setup:
        def args= argsStr.split(" ")

        when:
        def actual = totalController.run(args)

        // Mockをつくって、
        then:
        1 * monthlyAccumulatedHourReferenceService.refer(_) >> FixtureMonthlyAccumulatedWorkMinutes.get()
        actual == FixtureMonthlyAccumulatedWorkMinutes.get()

        where:
        label                       | argsStr
        "input arg is normal yearMonth"  | "total 202304"
    }

    def "TotalController irregular args: #label " () {
        setup:
        def args = argsStr.split(" ")

        when:
        totalController.run(args)

        then:
        Exception e = thrown()
        e.getMessage() == expected

        where:
        label                         | argsStr           || expected
        "Input arg is not yearMonth"  | "total 123456"    || "年月が不正です."
    }
}
