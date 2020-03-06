package com.naosim.dddwork.api


import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification

@ContextConfiguration(locations = ["classpath:context.xml"])
class AttendanceControllerSpec extends Specification {

    @Autowired
    private AttendanceController attendanceController;

    def setup() {
        File file = new File("data.csv");
        if (file.exists()) {
            file.delete();
        }
    }

    def cleanupSpec() {
        File file = new File("data.csv");
        if (file.exists()) {
            file.delete();
        }
    }

    static def countLines(File file) {
        def lineCount = 0
        file.eachLine("UTF-8") { lineCount++ }
        lineCount
    }

    def "標準勤務時間で登録"() {
        setup:
        def args = ['input', "20200201", "0900", "1800"] as String[]

        when:
        attendanceController.command(args)

        then:
        def file = new File("data.csv")
        1 == countLines(file)

        def lineData = ""
        file.eachLine {String line -> lineData = line }

        def fields = lineData.split(",")
        fields[0] == args[1]
        fields[1] == args[2]
        fields[2] == args[3]
        fields[3] == "480"   // 勤務時間
        fields[4] == "0"     // 残業時間
    }

    def "19時退社で残業は0で登録"() {
        setup:
        def args = ['input', "20200201", "0900", "1900"] as String[]

        when:
        attendanceController.command(args)

        then:
        def file = new File("data.csv")
        1 == countLines(file)

        def lineData = ""
        file.eachLine {String line -> lineData = line }

        def fields = lineData.split(",")
        fields[0] == args[1]
        fields[1] == args[2]
        fields[2] == args[3]
        fields[3] == "480"   // 勤務時間
        fields[4] == "0"     // 残業時間
    }

    def "21時退社で残業は2時間で登録"() {
        setup:
        def args = ['input', "20200201", "0900", "2100"] as String[]

        when:
        attendanceController.command(args)

        then:
        def file = new File("data.csv")
        1 == countLines(file)

        def lineData = ""
        file.eachLine {String line -> lineData = line }

        def fields = lineData.split(",")
        fields[0] == args[1]
        fields[1] == args[2]
        fields[2] == args[3]
        fields[3] == "600"   // 勤務時間
        fields[4] == "120"     // 残業時間
    }

    def "23時退社で残業は3で登録"() {
        setup:
        def args = ['input', "20200201", "0900", "2300"] as String[]

        when:
        attendanceController.command(args)

        then:
        def file = new File("data.csv")
        1 == countLines(file)

        def lineData = ""
        file.eachLine {String line -> lineData = line }

        def fields = lineData.split(",")
        fields[0] == args[1]
        fields[1] == args[2]
        fields[2] == args[3]
        fields[3] == "660"   // 勤務時間
        fields[4] == "180"     // 残業時間
    }

    def "25時退社の日付跨ぎで残業は24時退社で同じ"() {
        setup:
        def args = ['input', "20200201", "0900", "2500"] as String[]

        when:
        attendanceController.command(args)

        then:
        def file = new File("data.csv")
        1 == countLines(file)

        def lineData = ""
        file.eachLine {String line -> lineData = line }

        def fields = lineData.split(",")
        fields[0] == args[1]
        fields[1] == args[2]
        fields[2] == args[3]
//        fields[3] == "780"   // 勤務時間
//        fields[4] == "300"     // 残業時間

        // リファクタリング後は24時以降はサービス残業となる
        fields[3] == "720"   // 勤務時間
        fields[4] == "240"     // 残業時間
    }

    def "早出"() {
        setup:
        def args = ['input', "20200201", "0800", "1700"] as String[]

        when:
        attendanceController.command(args)

        then:
        def file = new File("data.csv")
        1 == countLines(file)

        def lineData = ""
        file.eachLine {String line -> lineData = line }

        def fields = lineData.split(",")
        fields[0] == args[1]
        fields[1] == args[2]
        fields[2] == args[3]
        fields[3] == "480"   // 勤務時間
        fields[4] == "0"     // 残業時間
    }

    def "遅刻"() {
        setup:
        def args = ['input', "20200201", "1000", "1900"] as String[]

        when:
        attendanceController.command(args)

        then:
        thrown(RuntimeException)

//        def file = new File("data.csv")
//        1 == countLines(file)
//
//        def lineData = ""
//        file.eachLine {String line -> lineData = line }
//
//        def fields = lineData.split(",")
//        fields[0] == args[1]
//        fields[1] == args[2]
//        fields[2] == args[3]
//        fields[3] == "420"   // 勤務時間
//        fields[4] == "0"     // 残業時間
    }

    def "出社時刻＞終了時刻"() {
        setup:
        def args = ['input', "20200201", "0900", "0100"] as String[]

        when:
        attendanceController.command(args)

        then:
        thrown(RuntimeException)

//        def file = new File("data.csv")
//        1 == countLines(file)
//
//        def lineData = ""
//        file.eachLine {String line -> lineData = line }
//
//        def fields = lineData.split(",")
//        fields[0] == args[1]
//        fields[1] == args[2]
//        fields[2] == args[3]
//        fields[3] == "-480"   // 勤務時間
//        fields[4] == "0"     // 残業時間
    }

    def "月次集計"() {
        setup:
        def savedSystemOut = System.out
        def printStream = Mock(PrintStream)
        System.out = printStream

        def args = ['total', "202002"] as String[]

        new File("data.csv") << new File("data_total.csv").readBytes()

        when:
        attendanceController.command(args)

        then:
        1 * printStream.println('勤務時間: 56時間15分')
        1 * printStream.println('残業時間: 9時間15分')

        cleanup:
        System.out = savedSystemOut
    }

    def "月次集計_対象データなし"() {
        setup:
        def savedSystemOut = System.out
        def printStream = Mock(PrintStream)
        System.out = printStream

        def args = ['total', "202004"] as String[]

        new File("data.csv") << new File("data_total.csv").readBytes()

        when:
        attendanceController.command(args)

        then:
        1 * printStream.println('勤務時間: 0時間0分')
        1 * printStream.println('残業時間: 0時間0分')

        cleanup:
        System.out = savedSystemOut
    }

    def "メソッドタイプ不正"() {
        setup:
        def args = ['output', "20200201", "0900", "1800"] as String[]

        when:
        attendanceController.command(args)

        then:
        thrown(RuntimeException)
    }

    def "引数が足りない"() {
        setup:
        def args = ['input', "20200201", "0900"] as String[]

        when:
        attendanceController.command(args)

        then:
        thrown(RuntimeException)
    }

    def "日付桁数不足"() {
        setup:
        def args = ['input', "2020021", "0900", "1800"] as String[]

        when:
        attendanceController.command(args)

        then:
        thrown(RuntimeException)
    }

    def "日付桁数超過"() {
        setup:
        def args = ['input', "202002011", "0900", "1800"] as String[]

        when:
        attendanceController.command(args)

        then:
        thrown(RuntimeException)
    }

    def "日付不正"() {
        setup:
        def args = ['input', "yyyymmdd", "0900", "1800"] as String[]

        when:
        attendanceController.command(args)

        then:
        thrown(RuntimeException)

//        def file = new File("data.csv")
//        1 == countLines(file)
//
//        def lineData = ""
//        file.eachLine {String line -> lineData = line }
//
//        def fields = lineData.split(",")
//        fields[0] == args[1]
//        fields[1] == args[2]
//        fields[2] == args[3]
//        fields[3] == "480"   // 勤務時間
//        fields[4] == "0"     // 残業時間
    }

    def "開始時刻不正_桁数不足"() {
        setup:
        def args = ['input', "20200201", "090", "1800"] as String[]

        when:
        attendanceController.command(args)

        then:
        thrown(RuntimeException)
    }

    def "開始時刻不正_桁数超過"() {
        setup:
        def args = ['input', "20200201", "09001", "1800"] as String[]

        when:
        attendanceController.command(args)

        then:
        thrown(RuntimeException)
    }

    def "開始時刻不正_文字列"() {
        setup:
        def args = ['input', "20200201", "AAAA", "1800"] as String[]

        when:
        attendanceController.command(args)

        then:
        thrown(RuntimeException)
    }

    def "開始時刻不正_不正時間"() {
        setup:
        def args = ['input', "20200201", "0990", "1800"] as String[]

        when:
        attendanceController.command(args)

        then:
        thrown(RuntimeException)

//        def file = new File("data.csv")
//        1 == countLines(file)
//
//        def lineData = ""
//        file.eachLine {String line -> lineData = line }
//
//        def fields = lineData.split(",")
//        fields[0] == args[1]
//        fields[1] == args[2]
//        fields[2] == args[3]
//        fields[3] == "390"   // 勤務時間
//        fields[4] == "0"     // 残業時間
    }

    def "終了時刻不正_桁数不足"() {
        setup:
        def args = ['input', "20200201", "0900", "180"] as String[]

        when:
        attendanceController.command(args)

        then:
        thrown(RuntimeException)
    }

    def "終了時刻不正_桁数超過"() {
        setup:
        def args = ['input', "20200201", "0900", "18001"] as String[]

        when:
        attendanceController.command(args)

        then:
        thrown(RuntimeException)
    }

    def "終了時刻不正_文字列"() {
        setup:
        def args = ['input', "20200201", "0900", "AAAA"] as String[]

        when:
        attendanceController.command(args)

        then:
        thrown(RuntimeException)
    }

    def "終了時刻不正_不正時間"() {
        setup:
        def args = ['input', "20200201", "0900", "1990"] as String[]

        when:
        attendanceController.command(args)

        then:
        thrown(RuntimeException)

//        def file = new File("data.csv")
//        1 == countLines(file)
//
//        def lineData = ""
//        file.eachLine {String line -> lineData = line }
//
//        def fields = lineData.split(",")
//        fields[0] == args[1]
//        fields[1] == args[2]
//        fields[2] == args[3]
//        fields[3] == "570"   // 勤務時間
//        fields[4] == "90"     // 残業時間
    }

    def "年月桁数不足"() {
        setup:
        def args = ['total', "20201"] as String[]
        new File("data.csv") << new File("data_total.csv").readBytes()

        when:
        attendanceController.command(args)

        then:
        thrown(RuntimeException)
    }

    def "年月桁数超過"() {
        setup:
        def args = ['total', "2020011"] as String[]
        new File("data.csv") << new File("data_total.csv").readBytes()

        when:
        attendanceController.command(args)

        then:
        thrown(RuntimeException)
    }

    def "年月不正"() {
        setup:
        def args = ['total', "yyyymm"] as String[]
        new File("data.csv") << new File("data_total.csv").readBytes()

        when:
        attendanceController.command(args)

        then:
        thrown(RuntimeException)
    }
}
