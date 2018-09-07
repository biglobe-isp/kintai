import kintai.Main
import org.springframework.beans.factory.annotation.Autowired
import spock.lang.Specification

class MainTest extends Specification {

    @Autowired
    Main main = new Main()

    def "input #testCase"() {

        when:
        main.main("input", date, start, end)

        then:
        notThrown()

        where:
        testCase             | date       | start  | end
        "20170101_0900_1800" | "20170101" | "0900" | "1800"
        "20170102_0900_1900" | "20170102" | "0900" | "1900"
        "20170103_0900_2000" | "20170103" | "0900" | "2000"
    }

    def "total"() {

        when:
        main.main("total", "201701")

        then:
        notThrown()
    }
}