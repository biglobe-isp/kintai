package org.example.controller

import spock.lang.Specification
import spock.lang.Subject

class CommandDispatchSpec extends Specification {
    private Command mockRegisterCommand = Mock()
    private Command mockGetTotalTimeCommand = Mock()

    @Subject
    private CommandDispatcher commandDispatcher

    def setup() {
        mockRegisterCommand.getCommandName() >> "input"
        mockGetTotalTimeCommand.getCommandName() >> "total"
        commandDispatcher = new CommandDispatcher([mockRegisterCommand, mockGetTotalTimeCommand])
    }


    def "登録コマンドが正常に実行される"() {
        when:
        commandDispatcher.dispatch(["input", "date:20231001", "start:09_00", "end:18_00"] as String[])

        then:
        1 * mockRegisterCommand.execute(["date:20231001", "start:09_00", "end:18_00"])
    }

    def "集計コマンドが正常に実行される"() {
        when:
        commandDispatcher.dispatch(["total", "yearMonth:2023_10"] as String[])

        then:
        1 * mockGetTotalTimeCommand.execute(["yearMonth:2023_10"])
    }
}
