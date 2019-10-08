import com.naosim.dddwork.domain.Rest
import com.naosim.dddwork.domain.RestTimeService
import spock.lang.Specification
import spock.lang.Unroll

import java.time.LocalTime

class RestTimeServiceTest extends Specification {
    @Unroll
    def "CalcurateRest"() {

        def rest = new Rest(12, 00, 13, 00);
        def rests = [rest] as Rest[];

        def policy = new RestTimeService(rests);
        def start = LocalTime.of(startH, startM);
        def end = LocalTime.of(endH, endM);
        expect:
        policy.calcurateRestTime(start, end) == result;
        where:
        startH | startM | endH | endM || result
        9      | 0      | 10   | 0    || 0
        9      | 0      | 12   | 10   || 10
        12     | 10     | 12   | 50   || 40
        12     | 50     | 13   | 50   || 10
        13     | 00     | 13   | 50   || 0


    }
}
