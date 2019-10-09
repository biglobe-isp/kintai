import com.naosim.dddwork.domain.Rest
import com.naosim.dddwork.domain.RestTimeService
import com.naosim.dddwork.domain.WorkTime
import spock.lang.Specification
import spock.lang.Unroll

import java.time.LocalTime

class RestTimeServiceTest extends Specification {
    @Unroll
    def "CalcurateRest"() {

        def rest = new Rest(12, 00, 13, 00);
        def rests = [rest] as Rest[];

        def policy = new RestTimeService(rests);
        def start = new WorkTime(startTime);
        def end = new WorkTime(endTime);
        expect:
        policy.calculateRestTime(start, end) == result;
        where:
        startTime | endTime || result
        "0900"    | "1000"  || 0
        "0900"    | "1210"  || 10
        "1210"    | "1250"  || 40
        "1250"    | "1350"  || 10
        "1300"    | "1350"  || 0


    }
}
