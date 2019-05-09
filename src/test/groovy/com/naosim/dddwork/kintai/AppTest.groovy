package com.naosim.dddwork.kintai

import spock.lang.Specification
import spock.lang.Unroll

class AppTest extends Specification {

    def CSV_FILE_NAME = "data.csv"

    @Unroll
    def "勤務時間-バリエーション: #caseName"() {

        setup: "データファイルが存在しない状態で"
        new File(CSV_FILE_NAME).delete()
        def outputStream = new ByteArrayOutputStream()
        def printStream = Spy(PrintStream, constructorArgs: [outputStream])
        System.out = printStream

        when: "日次勤怠を1件登録すると"
        App.main("input", date, begin, end)

        then: "月次勤務時間合計としては、登録した1件分の合計が得られる"
        App.main("total", date.substring(0, 6))
        outputStream.toString() == """|勤務時間: ${勤務H}時間${勤務M}分
                                      |残業時間: ${残業H}時間${残業M}分
                                      |""".stripMargin()

        where:
         date      | begin | end   || 勤務H | 勤務M | 残業H | 残業M || caseName
        "20170101" |"0900" |"1100" || 2     | 0    | 0    | 0     || "勤務時間-早退-午前"
        "20170101" |"0900" |"1100" || 2     | 0    | 0    | 0     || "勤務時間-早退-午前"
        "20170101" |"0900" |"1200" || 3     | 0    | 0    | 0     || "勤務時間-早退-昼休み-境界begin"
        "20170101" |"0900" |"1220" || 3     | 0    | 0    | 0     || "勤務時間-早退-昼休み-中間"
        "20170101" |"0900" |"1300" || 3     | 0    | 0    | 0     || "勤務時間-早退-昼休み-境界end"
        "20170101" |"0900" |"1540" || 5     | 40   | 0    | 0     || "勤務時間-早退-午後"
        "20170101" |"0900" |"1800" || 8     | 0    | 0    | 0     || "勤務時間-定時(夕休み-境界begin)"
        "20170101" |"0900" |"1810" || 8     | 0    | 0    | 0     || "勤務時間-定時-夕休み-中間"
        "20170101" |"0900" |"1900" || 8     | 0    | 0    | 0     || "勤務時間-定時-夕休み-境界end"
        "20170101" |"0900" |"2010" || 9     | 10   | 1    | 10    || "勤務時間-残業-晩"
        "20170101" |"0900" |"2100" || 10    | 0    | 2    | 0     || "勤務時間-残業-夜休み-境界begin"
        "20170101" |"0900" |"2150" || 10    | 0    | 2    | 0     || "勤務時間-残業-夜休み-中間"
        "20170101" |"0900" |"2200" || 10    | 0    | 2    | 0     || "勤務時間-残業-夜休み-境界end"
        "20170101" |"0900" |"2330" || 11    | 30   | 3    | 30    || "勤務時間-残業-深夜"
        "20170101" |"0900" |"2400" || 12    | 0    | 4    | 0     || "勤務時間-サービス残業-境界begin"
        "20170101" |"0900" |"2530" || 12    | 0    | 4    | 0     || "勤務時間-サービス残業-中間1"
        "20170101" |"0900" |"3100" || 12    | 0    | 4    | 0     || "勤務時間-サービス残業-中間2"
        "20170101" |"0900" |"2530" || 12    | 0    | 4    | 0     || "勤務時間-サービス残業-境界end"
    }



    def "修正登録-1回修正"() {

        setup: "データファイルが存在しない状態で"
        new File(CSV_FILE_NAME).delete()
        def outputStream = new ByteArrayOutputStream()
        def printStream = Spy(PrintStream, constructorArgs: [outputStream])
        System.out = printStream

        when: "同じ勤怠日付で2回登録すると"
        App.main("input", "20170101", "0900", "1800")
        App.main("input", "20170101", "0900", "2030")

        then: "最後に登録したデータが有効になる"
        App.main("total", "201701")
        outputStream.toString() == """|勤務時間: 9時間30分
                                      |残業時間: 1時間30分
                                      |""".stripMargin()
    }

    def "修正登録-2回修正"() {

        setup: "データファイルが存在しない状態で"
        new File(CSV_FILE_NAME).delete()
        def outputStream = new ByteArrayOutputStream()
        def printStream = Spy(PrintStream, constructorArgs: [outputStream])
        System.out = printStream

        when: "同じ勤怠日付で3回登録すると"
        App.main("input", "20170101", "0900", "1800")
        App.main("input", "20170101", "0900", "2030")
        App.main("input", "20170101", "0900", "1540")

        then: "最後に登録したデータが有効になる"
        App.main("total", "201701")
        outputStream.toString() == """|勤務時間: 5時間40分
                                      |残業時間: 0時間0分
                                      |""".stripMargin()
    }



    def "シナリオ-月合計-月抽出-修正入力あり"() {

        setup: "データファイルが存在しない状態で"
        new File(CSV_FILE_NAME).delete()
        def outputStream = new ByteArrayOutputStream()
        def printStream = Spy(PrintStream, constructorArgs: [outputStream])
        System.out = printStream

        when: "複数の月が存在するように登録すると"
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

        then: "指定された月についての合計が得られる"
        App.main("total", "201701")
        outputStream.toString() == """|勤務時間: 139時間20分
                                      |残業時間: 26時間40分
                                      |""".stripMargin()
    }

}
