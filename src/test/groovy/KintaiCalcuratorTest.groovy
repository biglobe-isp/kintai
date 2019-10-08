import domain.KintaiCalcurator
import spock.lang.Specification
import spock.lang.Unroll

class KintaiCalcuratorTest extends Specification {

    @Unroll
    def "CalcurateJiturodo"() {

        def kintai = new KintaiCalcurator(date, start, end);
        expect:
        kintai.getWorkMinutes() == result;
        where:
        date       | start  | end    || result
        "20191002" | "0900" | "1000" || 60
        "20191003" | "0900" | "1800" || 480
    }

    @Unroll
    def "CalcurateZangyo"() {
        def kintai = new KintaiCalcurator(date, start, end);
        expect:
        kintai.getOverWorkMinutes() == result;
        where:
        date       | start  | end    || result
        "20191002" | "0900" | "1800" || 0
        "20191003" | "0900" | "2000" || 60
    }
}
