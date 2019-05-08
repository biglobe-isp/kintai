package com.naosim.dddwork.kintai

import spock.lang.Specification

class AppTest extends Specification {

    def CSV_FILE_NAME = "data.csv"

    def "勤務時間-早退-午前"() {

        setup:
        new File(CSV_FILE_NAME).delete()
        def outputStream = new ByteArrayOutputStream()
        def printStream = Spy(PrintStream, constructorArgs: [outputStream])
        System.out = printStream

        when:
        App.main("input", "20170101", "0900", "1100")

        then:
        App.main("total", "201701")
        outputStream.toString() == """|勤務時間: 2時間0分
                                      |残業時間: 0時間0分
                                      |""".stripMargin()
    }

    def "勤務時間-早退-昼休み-境界begin"() {

        setup:
        new File(CSV_FILE_NAME).delete()
        def outputStream = new ByteArrayOutputStream()
        def printStream = Spy(PrintStream, constructorArgs: [outputStream])
        System.out = printStream

        when:
        App.main("input", "20170101", "0900", "1200")

        then:
        App.main("total", "201701")
        outputStream.toString() == """|勤務時間: 3時間0分
                                      |残業時間: 0時間0分
                                      |""".stripMargin()
    }

    def "勤務時間-早退-昼休み-中間"() {

        setup:
        new File(CSV_FILE_NAME).delete()
        def outputStream = new ByteArrayOutputStream()
        def printStream = Spy(PrintStream, constructorArgs: [outputStream])
        System.out = printStream

        when:
        App.main("input", "20170101", "0900", "1220")

        then:
        App.main("total", "201701")
        outputStream.toString() == """|勤務時間: 3時間0分
                                      |残業時間: 0時間0分
                                      |""".stripMargin()
    }

    def "勤務時間-早退-昼休み-境界end"() {

        setup:
        new File(CSV_FILE_NAME).delete()
        def outputStream = new ByteArrayOutputStream()
        def printStream = Spy(PrintStream, constructorArgs: [outputStream])
        System.out = printStream

        when:
        App.main("input", "20170101", "0900", "1300")

        then:
        App.main("total", "201701")
        outputStream.toString() == """|勤務時間: 3時間0分
                                      |残業時間: 0時間0分
                                      |""".stripMargin()
    }

    def "勤務時間-早退-午後"() {

        setup:
        new File(CSV_FILE_NAME).delete()
        def outputStream = new ByteArrayOutputStream()
        def printStream = Spy(PrintStream, constructorArgs: [outputStream])
        System.out = printStream

        when:
        App.main("input", "20170101", "0900", "1540")

        then:
        App.main("total", "201701")
        outputStream.toString() == """|勤務時間: 5時間40分
                                      |残業時間: 0時間0分
                                      |""".stripMargin()
    }

    def "勤務時間-定時(夕休み-境界begin)"() {

        setup:
        new File(CSV_FILE_NAME).delete()
        def outputStream = new ByteArrayOutputStream()
        def printStream = Spy(PrintStream, constructorArgs: [outputStream])
        System.out = printStream

        when:
        App.main("input", "20170101", "0900", "1800")

        then:
        App.main("total", "201701")
        outputStream.toString() == """|勤務時間: 8時間0分
                                      |残業時間: 0時間0分
                                      |""".stripMargin()
    }

    def "勤務時間-定時-夕休み-中間"() {

        setup:
        new File(CSV_FILE_NAME).delete()
        def outputStream = new ByteArrayOutputStream()
        def printStream = Spy(PrintStream, constructorArgs: [outputStream])
        System.out = printStream

        when:
        App.main("input", "20170101", "0900", "1810")

        then:
        App.main("total", "201701")
        outputStream.toString() == """|勤務時間: 8時間0分
                                      |残業時間: 0時間0分
                                      |""".stripMargin()
    }

    def "勤務時間-定時-夕休み-境界end"() {

        setup:
        new File(CSV_FILE_NAME).delete()
        def outputStream = new ByteArrayOutputStream()
        def printStream = Spy(PrintStream, constructorArgs: [outputStream])
        System.out = printStream

        when:
        App.main("input", "20170101", "0900", "1900")

        then:
        App.main("total", "201701")
        outputStream.toString() == """|勤務時間: 8時間0分
                                      |残業時間: 0時間0分
                                      |""".stripMargin()
    }

    def "勤務時間-残業-晩"() {

        setup:
        new File(CSV_FILE_NAME).delete()
        def outputStream = new ByteArrayOutputStream()
        def printStream = Spy(PrintStream, constructorArgs: [outputStream])
        System.out = printStream

        when:
        App.main("input", "20170101", "0900", "2010")

        then:
        App.main("total", "201701")
        outputStream.toString() == """|勤務時間: 9時間10分
                                      |残業時間: 1時間10分
                                      |""".stripMargin()
    }

    def "勤務時間-残業-夜休み-境界begin"() {

        setup:
        new File(CSV_FILE_NAME).delete()
        def outputStream = new ByteArrayOutputStream()
        def printStream = Spy(PrintStream, constructorArgs: [outputStream])
        System.out = printStream

        when:
        App.main("input", "20170101", "0900", "2100")

        then:
        App.main("total", "201701")
        outputStream.toString() == """|勤務時間: 10時間0分
                                      |残業時間: 2時間0分
                                      |""".stripMargin()
    }

    def "勤務時間-残業-夜休み-中間"() {

        setup:
        new File(CSV_FILE_NAME).delete()
        def outputStream = new ByteArrayOutputStream()
        def printStream = Spy(PrintStream, constructorArgs: [outputStream])
        System.out = printStream

        when:
        App.main("input", "20170101", "0900", "2150")

        then:
        App.main("total", "201701")
        outputStream.toString() == """|勤務時間: 10時間0分
                                      |残業時間: 2時間0分
                                      |""".stripMargin()
    }

    def "勤務時間-残業-夜休み-境界end"() {

        setup:
        new File(CSV_FILE_NAME).delete()
        def outputStream = new ByteArrayOutputStream()
        def printStream = Spy(PrintStream, constructorArgs: [outputStream])
        System.out = printStream

        when:
        App.main("input", "20170101", "0900", "2200")

        then:
        App.main("total", "201701")
        outputStream.toString() == """|勤務時間: 10時間0分
                                      |残業時間: 2時間0分
                                      |""".stripMargin()
    }

    def "勤務時間-残業-深夜"() {

        setup:
        new File(CSV_FILE_NAME).delete()
        def outputStream = new ByteArrayOutputStream()
        def printStream = Spy(PrintStream, constructorArgs: [outputStream])
        System.out = printStream

        when:
        App.main("input", "20170101", "0900", "2330")

        then:
        App.main("total", "201701")
        outputStream.toString() == """|勤務時間: 11時間30分
                                      |残業時間: 3時間30分
                                      |""".stripMargin()
    }

    def "勤務時間-サービス残業-境界begin"() {

        setup:
        new File(CSV_FILE_NAME).delete()
        def outputStream = new ByteArrayOutputStream()
        def printStream = Spy(PrintStream, constructorArgs: [outputStream])
        System.out = printStream

        when:
        App.main("input", "20170101", "0900", "2400")

        then:
        App.main("total", "201701")
        outputStream.toString() == """|勤務時間: 12時間0分
                                      |残業時間: 4時間0分
                                      |""".stripMargin()
    }

    def "勤務時間-サービス残業-中間1"() {

        setup:
        new File(CSV_FILE_NAME).delete()
        def outputStream = new ByteArrayOutputStream()
        def printStream = Spy(PrintStream, constructorArgs: [outputStream])
        System.out = printStream

        when:
        App.main("input", "20170101", "0900", "2530")

        then:
        App.main("total", "201701")
        outputStream.toString() == """|勤務時間: 12時間0分
                                      |残業時間: 4時間0分
                                      |""".stripMargin()
    }

    def "勤務時間-サービス残業-中間2"() {

        setup:
        new File(CSV_FILE_NAME).delete()
        def outputStream = new ByteArrayOutputStream()
        def printStream = Spy(PrintStream, constructorArgs: [outputStream])
        System.out = printStream

        when:
        App.main("input", "20170101", "0900", "3100")

        then:
        App.main("total", "201701")
        outputStream.toString() == """|勤務時間: 12時間0分
                                      |残業時間: 4時間0分
                                      |""".stripMargin()
    }

    def "勤務時間-サービス残業-境界end"() {

        setup:
        new File(CSV_FILE_NAME).delete()
        def outputStream = new ByteArrayOutputStream()
        def printStream = Spy(PrintStream, constructorArgs: [outputStream])
        System.out = printStream

        when:
        App.main("input", "20170101", "0900", "3300")

        then:
        App.main("total", "201701")
        outputStream.toString() == """|勤務時間: 12時間0分
                                      |残業時間: 4時間0分
                                      |""".stripMargin()
    }



    def "修正登録-1回修正"() {

        setup:
        new File(CSV_FILE_NAME).delete()
        def outputStream = new ByteArrayOutputStream()
        def printStream = Spy(PrintStream, constructorArgs: [outputStream])
        System.out = printStream

        when:
        App.main("input", "20170101", "0900", "1800")
        App.main("input", "20170101", "0900", "2030")

        then:
        App.main("total", "201701")
        outputStream.toString() == """|勤務時間: 9時間30分
                                      |残業時間: 1時間30分
                                      |""".stripMargin()
    }

    def "修正登録-2回修正"() {

        setup:
        new File(CSV_FILE_NAME).delete()
        def outputStream = new ByteArrayOutputStream()
        def printStream = Spy(PrintStream, constructorArgs: [outputStream])
        System.out = printStream

        when:
        App.main("input", "20170101", "0900", "1800")
        App.main("input", "20170101", "0900", "2030")
        App.main("input", "20170101", "0900", "1540")

        then:
        App.main("total", "201701")
        outputStream.toString() == """|勤務時間: 5時間40分
                                      |残業時間: 0時間0分
                                      |""".stripMargin()
    }



    def "シナリオ-月合計-月抽出-修正入力あり"() {

        setup:
        new File(CSV_FILE_NAME).delete()
        def outputStream = new ByteArrayOutputStream()
        def printStream = Spy(PrintStream, constructorArgs: [outputStream])
        System.out = printStream

        when:
        App.main("input", "20161231", "0900", "1800")   // total対象外
        App.main("input", "20170101", "0900", "1100")
        App.main("input", "20170102", "0900", "1130")
        App.main("input", "20170102", "0900", "1200")   // 修正入力
        App.main("input", "20170103", "0900", "1220")
        App.main("input", "20170104", "0900", "1300")
        App.main("input", "20170105", "0900", "1540")
        App.main("input", "20170108", "0900", "1800")
        App.main("input", "20170109", "0900", "1810")
        App.main("input", "20170110", "0900", "1900")
        App.main("input", "20170115", "0900", "2040")
        App.main("input", "20170115", "0900", "2010")   // 修正入力
        App.main("input", "20170116", "0900", "2100")
        App.main("input", "20170117", "0900", "2150")
        App.main("input", "20170123", "0900", "2200")
        App.main("input", "20170124", "0900", "2330")
        App.main("input", "20170125", "0900", "2400")
        App.main("input", "20170129", "0900", "2530")
        App.main("input", "20170130", "0900", "3100")
        App.main("input", "20170131", "0900", "3300")
        App.main("input", "20170201", "0900", "1800")   // total対象外

        then:
        App.main("total", "201701")
        outputStream.toString() == """|勤務時間: 139時間20分
                                      |残業時間: 26時間40分
                                      |""".stripMargin()
    }

}
