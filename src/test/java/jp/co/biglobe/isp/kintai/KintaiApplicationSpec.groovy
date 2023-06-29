package jp.co.biglobe.isp.kintai

import org.springframework.boot.SpringApplication
import spock.lang.Specification
import spock.lang.Unroll

@Unroll
class KintaiApplicationSpec extends Specification{

    def "KintaiApplication: #label" () {
        when:
        actual = SpringApplication.run(KintaiApplication.class, sut)

        then:
        IllegalArgumentException e = thrown()
        e.getMessage() == expexted

        where:
        label            | sut                            || expected
    }

}
