import domain.KintaiCalcurator
import spock.lang.Specification
import spock.lang.Unroll

class KintaiCalcuratorTest extends Specification {

    @Unroll
    def "CalcurateJiturodo"() {

        def kintai = new KintaiCalcurator(startH, startM, endH, endM);
        kintai.calcurateJiturodo();
        expect:
        kintai.getWorkMinutes() == result;
        where:
        startH | startM | endH | endM || result
        9      | 0      | 10   | 0    || 60
    }

    def "CalcurateZangyo"() {
    }
}
