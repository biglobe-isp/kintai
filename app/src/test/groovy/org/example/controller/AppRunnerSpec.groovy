package org.example.controller

import org.example.domain.FixtureWorkTime
import org.example.domain.RegisterInput
import org.example.domain.WorkInformation
import org.example.domain.WorkTime
import org.example.service.WorkInformationService
import org.spockframework.spring.SpringBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification

@SpringBootTest
class AppRunnerSpec extends Specification {
    @Autowired
    CommandDispatcher commandDispatcher

    @SpringBean
    WorkInformationService workInformationService = Mock()

    def "存在するすべてのCommandがCommandDispatcherにDIされている"() {
        expect:
        commandDispatcher.commands.size() >= 2
        commandDispatcher.commands.containsKey("input")
        commandDispatcher.commands.containsKey("total")
    }

    def "RegisterCommandのexcuteが正常に実行される"() {
        when:
        commandDispatcher.dispatch(["input", "date:20231001", "start:09_00", "end:18_00"] as String[])

        then:
        1 * workInformationService.register( ri ->{
            assert ri instanceof RegisterInput
            assert ri.getDateToRegisterString() == "20231001"
            assert ri.getStartTimeString() == "09_00"
            assert ri.getEndTimeString() == "18_00"
        }) >> true
    }

    def "RegisterCommandのが正常に実行される"() {
        when:
        def result = commandDispatcher.dispatch(["total", "-yearMonth:202302"] as String[])

        then:
        1 * workInformationService.getTotalWorkTime(ym -> {
            assert ym == "202302"
        }) >> FixtureWorkTime.get()

    }
}
