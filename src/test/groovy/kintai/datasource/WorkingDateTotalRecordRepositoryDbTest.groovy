package kintai.datasource

import kintai.domain.FixtureWorkingDateTotalRecordList
import lombok.RequiredArgsConstructor
import spock.lang.Specification
import kintai.datasource.ExportWorkingDateTotalRecordRepositoryDb

import java.nio.file.Files
import java.nio.file.Path
import java.time.LocalDateTime
import java.time.YearMonth

@RequiredArgsConstructor
class WorkingDateTotalRecordRepositoryDbTest extends Specification {

    def "就業日合計記録をCSVへ保存する"(){
        setup:
        def tmpCsvFile = Files.createTempFile("tmp","data.csv")
        def sut  = new WorkingDateTotalRecordRepositoryDb(tmpCsvFile)
        def input = FixtureWorkingDateTotalRecord.get()
        def now = LocalDateTime.parse("2023-08-18T15:45:33")


        when:
        sut.save(input,now)
        //def expected = new ExportWorkingDateTotalRecordRepositoryDb()
        then:
        //CSVから取得したデータとinputが同じならok
        Files.readAllLines(tmpCsvFile) == List.of(
                        "20230816,0900,2000,540,60,2023-08-18T15:45:33"
        )
    }
    def "CSVに保存された勤務実績一覧から指定された月の分の勤務実績をリストで取り出す"(){
        setup:
        def sut = new WorkingDateTotalRecordRepositoryDb(Path.of("data.csv"))
        def input = YearMonth.of(2023, 8)

        when:
        def actual = sut.findByMonth(input)

        then:
        actual == FixtureCsvWorkingTotal.get()

    }
}
