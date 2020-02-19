package com.naosim.dddwork.api

import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest
class MainSpec extends Specification {
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
        def main = new Main();
        def args = ['input', "20200201", "0900", "1800"] as String[]

        when:
        main.main(args)

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
        def main = new Main();
        def args = ['input', "20200201", "0900", "1900"] as String[]
        def expected = [args[1],args[2],args[3],"480","0"]

        when:
        main.main(args)

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
        def main = new Main();
        def args = ['input', "20200201", "0900", "2100"] as String[]

        when:
        main.main(args)

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
        def main = new Main();
        def args = ['input', "20200201", "0900", "2300"] as String[]

        when:
        main.main(args)

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
        def main = new Main();
        def args = ['input', "20200201", "0900", "2500"] as String[]

        when:
        main.main(args)

        then:
        def file = new File("data.csv")
        1 == countLines(file)

        def lineData = ""
        file.eachLine {String line -> lineData = line }

        def fields = lineData.split(",")
        fields[0] == args[1]
        fields[1] == args[2]
        fields[2] == args[3]
        fields[3] == "780"   // 勤務時間
        fields[4] == "300"     // 残業時間

    }

    def "早出"() {
        setup:
        def main = new Main();
        def args = ['input', "20200201", "0800", "1700"] as String[]

        when:
        main.main(args)

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
        def main = new Main();
        def args = ['input', "20200201", "1000", "1900"] as String[]

        when:
        main.main(args)

        then:
        def file = new File("data.csv")
        1 == countLines(file)

        def lineData = ""
        file.eachLine {String line -> lineData = line }

        def fields = lineData.split(",")
        fields[0] == args[1]
        fields[1] == args[2]
        fields[2] == args[3]
        fields[3] == "420"   // 勤務時間
        fields[4] == "0"     // 残業時間

    }

    def "出社時刻＞終了時刻"() {
        setup:
        def main = new Main();
        def args = ['input', "20200201", "0900", "0100"] as String[]

        when:
        main.main(args)

        then:
        def file = new File("data.csv")
        1 == countLines(file)

        def lineData = ""
        file.eachLine {String line -> lineData = line }

        def fields = lineData.split(",")
        fields[0] == args[1]
        fields[1] == args[2]
        fields[2] == args[3]
        fields[3] == "-480"   // 勤務時間
        fields[4] == "0"     // 残業時間

    }

    def "月次集計"() {
        setup:
        def savedSystemOut = System.out
        def printStream = Mock(PrintStream)
        System.out = printStream

        def main = new Main();
        def args = ['total', "202002"] as String[]

        new File("data.csv") << new File("data_total.csv").readBytes()

        when:
        main.main(args)

        then:
        1 * printStream.println('勤務時間: 56時間15分')
        1 * printStream.println('残業時間: 9時間15分')
        
        cleanup:
        System.out = savedSystemOut
    }

    def "メソッドタイプ不正"() {
        setup:
        def main = new Main();
        def args = ['output', "20200201", "0900", "1800"] as String[]

        when:
        main.main(args)

        then:
        // TODO:e.printStackTraceをテストできないため何も登録されないことを確認
        def file = new File("data.csv")
        !file.exists()
    }

    def "引数が足りない"() {
        setup:
        def main = new Main();
        def args = ['input', "20200201", "0900"] as String[]

        when:
        main.main(args)

        then:
        // TODO:e.printStackTraceをテストできないため何も登録されないことを確認
        def file = new File("data.csv")
        !file.exists()
    }

    def "日付不正"() {
        setup:
        def main = new Main();
        def args = ['input', "yyyymmdd", "0900", "1800"] as String[]

        when:
        main.main(args)

        then:
        // TODO:不正な日付でも登録されてしまう
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

    def "開始時刻不正_文字列"() {
        setup:
        def main = new Main();
        def args = ['input', "20200201", "AAAA", "1800"] as String[]

        when:
        main.main(args)

        then:
        // TODO:e.printStackTraceをテストできないため何も登録されないことを確認
        def file = new File("data.csv")
        !file.exists()
    }

    def "開始時刻不正_不正時間"() {
        setup:
        def main = new Main();
        def args = ['input', "20200201", "0990", "1800"] as String[]

        when:
        main.main(args)

        then:
        // TODO:不正な日付でも登録されてしまう
        def file = new File("data.csv")
        1 == countLines(file)

        def lineData = ""
        file.eachLine {String line -> lineData = line }

        def fields = lineData.split(",")
        fields[0] == args[1]
        fields[1] == args[2]
        fields[2] == args[3]
        fields[3] == "390"   // 勤務時間
        fields[4] == "0"     // 残業時間
    }

    def "終了時刻不正_文字列"() {
        setup:
        def main = new Main();
        def args = ['input', "20200201", "0900", "AAAA"] as String[]

        when:
        main.main(args)

        then:
        // TODO:e.printStackTraceをテストできないため何も登録されないことを確認
        def file = new File("data.csv")
        !file.exists()

    }

    def "終了時刻不正_不正時間"() {
        setup:
        def main = new Main()
        def args = ['input', "20200201", "0900", "1990"] as String[]

        when:
        main.main(args)

        then:
        // TODO:不正な日付でも登録されてしまう
        def file = new File("data.csv")
        1 == countLines(file)

        def lineData = ""
        file.eachLine {String line -> lineData = line }

        def fields = lineData.split(",")
        fields[0] == args[1]
        fields[1] == args[2]
        fields[2] == args[3]
        fields[3] == "570"   // 勤務時間
        fields[4] == "90"     // 残業時間
    }

// TODO:無限ループに陥る
//    def "年月不正"() {
//        setup:
//        def savedSystemOut = System.out
//        def printStream = Mock(PrintStream)
//        System.out = printStream
//
//        def main = new Main();
//        def args = ['total', "yyyymm"] as String[]
//
//        new File("data.csv") << new File("data_total.csv").readBytes()
//
//        when:
//        main.main(args)
//
//        then:
//        1 * printStream.println('勤務時間: 0時間0分')
//        1 * printStream.println('残業時間: 0時間0分')
//
//        cleanup:
//        System.out = savedSystemOut
//    }
}
