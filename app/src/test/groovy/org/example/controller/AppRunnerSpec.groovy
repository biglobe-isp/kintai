package org.example.controller

import org.example.domain.FixtureWorkTime
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
@ContextConfiguration(classes = [RegisterCommand, CommandDispatcher, GetTotalTimeCommand])
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
        1 * workInformationService.register( wi ->{
            assert wi instanceof WorkInformation
            assert wi.getDateToRegister().getValue().toString() == "2023-10-01"
            assert wi.getWorkTime().getWorkStartTime().getValue().toString() == "09:00"
            assert wi.getWorkTime().getTotalWorkingHours().getValue().toString() == "480"
        }) >> true
    }

    def "RegisterCommandのが正常に実行される"() {
        when:
        def result = commandDispatcher.dispatch(["total", "-yearMonth:2023_02"] as String[])

        then:
        1 * workInformationService.getTotalWorkTime(ym -> {
            assert ym.toString() == "2023-02"
        }) >> FixtureWorkTime.get()

    }
}
