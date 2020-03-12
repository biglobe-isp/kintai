package com.naosim.dddwork.api

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification
import spock.lang.Unroll

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

    @Unroll
    def "登録バリエーションテスト"() {
        setup:
        def args = [type, day, start, end] as String[]

        expect:
        expectedValue == attendanceController.command(args)

        def file = new File("data.csv")
        countlines == countLines(file)

        def lineData = ""
        file.eachLine {String line -> lineData = line }

        def fields = lineData.split(",")
        fields1 == fields[0]
        fields2 == fields[1]
        fields3 == fields[2]
        fields4 == fields[3]
        fields5 == fields[4]

        where:
        type    | day        | start  | end    || expectedValue | countlines | fields1    | fields2 | fields3 | fields4 | fields5
        'input' | '20200201' | '0900' | '1800' || "Success"     | 1          | '20200201' | '0900'  | '1800'  | '480'   | '0'
        'input' | '20200201' | '0800' | '1700' || "Success"     | 1          | '20200201' | '0800'  | '1700'  | '480'   | '0'
        'input' | '20200201' | '0900' | '1900' || "Success"     | 1          | '20200201' | '0900'  | '1900'  | '480'   | '0'
        'input' | '20200201' | '0900' | '2100' || "Success"     | 1          | '20200201' | '0900'  | '2100'  | '600'   | '120'
        'input' | '20200201' | '0900' | '2100' || "Success"     | 1          | '20200201' | '0900'  | '2100'  | '600'   | '120'
        'input' | '20200201' | '0900' | '2300' || "Success"     | 1          | '20200201' | '0900'  | '2300'  | '660'   | '180'
        'input' | '20200201' | '0900' | '2500' || "Success"     | 1          | '20200201' | '0900'  | '2500'  | '720'   | '240'

    }

    def "遅刻"() {
        setup:
        def args = ['input', "20200201", "1000", "1900"] as String[]

        expect:
        "Success" != attendanceController.command(args)
    }

    def "出社時刻＞終了時刻"() {
        setup:
        def args = ['input', "20200201", "0900", "0100"] as String[]

        expect:
        "Success" != attendanceController.command(args)
    }

    def "月次集計"() {
        setup:
        def args = ['total', "202002"] as String[]

        new File("data.csv") << new File("data_total.csv").readBytes()

        def expected = '勤務時間: 56時間15分' + System.lineSeparator() + '残業時間: 9時間15分'

        expect:
        expected == attendanceController.command(args)
    }

    def "月次集計_対象データなし"() {
        setup:
        def args = ['total', "202004"] as String[]

        new File("data.csv") << new File("data_total.csv").readBytes()

        def expected = '勤務時間: 0時間0分' + System.lineSeparator() + '残業時間: 0時間0分'

        expect:
        expected == attendanceController.command(args)
    }

    def "月次集計_csvなし"() {
        setup:
        def args = ['total', "202004"] as String[]

        expect:
        "Success" != attendanceController.command(args)
    }

    def "メソッドタイプ不正"() {
        setup:
        def args = ['output', "20200201", "0900", "1800"] as String[]

        expect:
        "Success" != attendanceController.command(args)
    }

    def "引数が足りない(input)"() {
        setup:
        def args = ['input', "20200201", "0900"] as String[]

        expect:
        "Success" != attendanceController.command(args)
    }

    def "引数が足りない(total)"() {
        setup:
        def args = ['total'] as String[]

        expect:
        "Success" != attendanceController.command(args)
    }

    def "日付桁数不足"() {
        setup:
        def args = ['input', "2020021", "0900", "1800"] as String[]

        expect:
        "Success" != attendanceController.command(args)
    }

    def "日付桁数超過"() {
        setup:
        def args = ['input', "202002011", "0900", "1800"] as String[]

        expect:
        "Success" != attendanceController.command(args)
    }

    def "日付不正"() {
        setup:
        def args = ['input', "yyyymmdd", "0900", "1800"] as String[]

        expect:
        "Success" != attendanceController.command(args)
    }

    def "開始時刻不正_桁数不足"() {
        setup:
        def args = ['input', "20200201", "090", "1800"] as String[]

        expect:
        "Success" != attendanceController.command(args)
    }

    def "開始時刻不正_桁数超過"() {
        setup:
        def args = ['input', "20200201", "09001", "1800"] as String[]

        expect:
        "Success" != attendanceController.command(args)
    }

    def "開始時刻不正_文字列"() {
        setup:
        def args = ['input', "20200201", "AAAA", "1800"] as String[]

        expect:
        "Success" != attendanceController.command(args)
    }

    def "開始時刻不正_不正時間"() {
        setup:
        def args = ['input', "20200201", "0990", "1800"] as String[]

        expect:
        "Success" != attendanceController.command(args)
    }

    def "終了時刻不正_桁数不足"() {
        setup:
        def args = ['input', "20200201", "0900", "180"] as String[]

        expect:
        "Success" != attendanceController.command(args)
    }

    def "終了時刻不正_桁数超過"() {
        setup:
        def args = ['input', "20200201", "0900", "18001"] as String[]

        expect:
        "Success" != attendanceController.command(args)
    }

    def "終了時刻不正_文字列"() {
        setup:
        def args = ['input', "20200201", "0900", "AAAA"] as String[]

        expect:
        "Success" != attendanceController.command(args)
    }

    def "終了時刻不正_不正時間"() {
        setup:
        def args = ['input', "20200201", "0900", "1990"] as String[]

        expect:
        "Success" != attendanceController.command(args)
    }

    def "年月桁数不足"() {
        setup:
        def args = ['total', "20201"] as String[]
        new File("data.csv") << new File("data_total.csv").readBytes()

        expect:
        "Success" != attendanceController.command(args)
    }

    def "年月桁数超過"() {
        setup:
        def args = ['total', "2020011"] as String[]
        new File("data.csv") << new File("data_total.csv").readBytes()

        expect:
        "Success" != attendanceController.command(args)
    }

    def "年月不正"() {
        setup:
        def args = ['total', "yyyymm"] as String[]
        new File("data.csv") << new File("data_total.csv").readBytes()

        expect:
        "Success" != attendanceController.command(args)
    }
}
