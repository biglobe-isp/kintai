package kintai.datasource

import kintai.domain.FixtureAttendance
import spock.lang.Specification

import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.time.LocalDateTime
import java.time.YearMonth

import static java.nio.file.StandardOpenOption.CREATE

class AttendanceRepositoryCsvSpec extends Specification{

    private AttendanceMapperCsv attendanceMapperCsv = new AttendanceMapperCsvImpl()

    private String pathName = "test.csv"

    private AttendanceRepositoryCsv repository = new AttendanceRepositoryCsv(attendanceMapperCsv,Paths.get("test.csv"))

    def "勤怠情報を登録する"() {
        setup:
        def path = Paths.get(pathName)
        def attendance = FixtureAttendance.getAttendance1()
        def file = Files.createFile(path)

        when:
        repository.persist(attendance)

        then:
        def result = readTestCsvFile(file)
        result[0] == attendance.getAttendanceDate().format()
        result[1] == attendance.getAttendanceTime().formatFrom()
        result[2] == attendance.getAttendanceTime().formatTo()
        result[3] == attendance.getWorkDuration().getDuration().getSeconds().toString()
        result[4] == attendance.getOverWorkDuration().getDuration().getSeconds().toString()

        cleanup:
        Files.delete(path)
    }

    def "勤怠情報をリストで取得する"() {
        setup:
        def yearMonth = YearMonth.of(2021,10)
        def path = Paths.get(pathName)
        def file = Files.createFile(path)
        def expected = List.of(
                FixtureAttendance.getAttendance1(),
                FixtureAttendance.getAttendance2(),
                FixtureAttendance.getAttendance3())
        writeTestCsvFile(file,expected)

        when:
        def result = repository.select(yearMonth)

        then:
        result == expected

        cleanup:
        Files.delete(path)
    }

    /**
     * テストCSVファイル読み込み
     *
     * @param file ファイル
     * @return 読み込んだファイルの内容
     */
    private static String[] readTestCsvFile(Path path) {
        try (
                BufferedReader br = Files.newBufferedReader(path)
        ) {
            String line = br.readLine()
            return line.split(",")
        } catch (IOException e) {
            throw new RuntimeException("テスト失敗");
        }
    }

    /**
     * テストCSVファイル書き込み
     *
     * @param path ファイル
     * @param attendanceList 勤怠情報リスト
     */
    private static void writeTestCsvFile(path,attendanceList) {
        try (BufferedWriter fileWriter = Files.newBufferedWriter(path, CREATE)) {
            attendanceList.forEach(attendance -> {
                fileWriter.write(String.format(
                        "%s,%s,%s,%s,%s,%s\n",
                        attendance.getAttendanceDate().format(),
                        attendance.getAttendanceTime().formatFrom(),
                        attendance.getAttendanceTime().formatTo(),
                        attendance.getWorkDuration().getDuration().getSeconds(),
                        attendance.getOverWorkDuration().getDuration().getSeconds(),
                        LocalDateTime.now()
                ))
            })
        } catch (IOException e) {
            throw new RuntimeException("CSV書き込み失敗");
        }
    }
}
