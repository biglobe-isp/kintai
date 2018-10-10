package jp.co.biglobe.kintai.service

import jp.co.biglobe.kintai.datasource.TimeCardRepositoryDb
import jp.co.biglobe.kintai.domain.WorkTimeRepository
import spock.lang.Specification
import spock.lang.Unroll

@Unroll
class KintaiServiceSpec extends Specification {

    def "input呼び出しのテスト"(){
        setup:
        WorkTimeRepository repository = Mock()
        KintaiService service = new KintaiService(repository)

        when:
        service.input("20181001","0900","1800","1500")

        then:
        1 * repository.input(_)
    }

    def "total呼び出しのテスト"(){
        setup:
        def repository = new TimeCardRepositoryDb()
        def service = new KintaiService(repository)

        when:
        service.total("201810")

        then:
        1 * repository.findWorkTimeCard(_)

    }
}
