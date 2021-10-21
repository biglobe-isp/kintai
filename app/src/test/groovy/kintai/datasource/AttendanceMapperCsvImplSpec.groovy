package kintai.datasource

import kintai.domain.FixtureAttendance
import spock.lang.Specification

import java.time.LocalDateTime
import java.time.YearMonth

class AttendanceMapperCsvImplSpec extends Specification {
    //テスト対象のMapper
    private AttendanceMapperCsvImpl attendanceMapperCsv = new AttendanceMapperCsvImpl()

    def "CSVに勤怠情報を登録"() {
        setup:
        def fileName = "test.csv"
        def attendance = FixtureAttendance.getAttendance1()
        def file = new File(fileName)

        when:
        attendanceMapperCsv.save(fileName, attendance)

        then:
        def result = readTestCsvFile(file)
        result[0] == attendance.getAttendanceDate().format()
        result[1] == attendance.getAttendanceTime().formatStart()
        result[2] == attendance.getAttendanceTime().formatEnd()
        result[3] == attendance.getWorkDuration().getDuration().getSeconds().toString()
        result[4] == attendance.getOverWorkDuration().getDuration().getSeconds().toString()

        cleanup:
        file.delete()
    }

    def "年月で勤怠情報を検索"() {
        setup:
        def fileName = "test.csv"
        def file = new File(fileName)
        def expected = List.of(
                FixtureAttendance.getAttendance1(),
                FixtureAttendance.getAttendance2(),
                FixtureAttendance.getAttendance3())
        writeTestCsvFile(file,expected)

        when:
        def result = attendanceMapperCsv.findByYearMonth(
                fileName, YearMonth.of(2021,10))

        then:
        result == expected

        cleanup:
        file.delete()
    }
    /**
     * テストCSVファイル読み込み
     *
     * @param file ファイル
     * @return 読み込んだファイルの内容
     */
    private static String[] readTestCsvFile(File file) {
        try (
                FileReader fr = new FileReader(file);
                BufferedReader br = new BufferedReader(fr)
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
     * @param file ファイル
     * @param attendanceList 勤怠情報リスト
     */
    private static void writeTestCsvFile(file,attendanceList) {
        attendanceList.forEach(attendance -> {
            try (FileWriter fileWriter = new FileWriter(file, true)) {
                fileWriter.write(String.format(
                        "%s,%s,%s,%s,%s,%s\n",
                        attendance.getAttendanceDate().format(),
                        attendance.getAttendanceTime().formatStart(),
                        attendance.getAttendanceTime().formatEnd(),
                        attendance.getWorkDuration().getDuration().getSeconds(),
                        attendance.getOverWorkDuration().getDuration().getSeconds(),
                        LocalDateTime.now()
                ))
            } catch (IOException e) {
                throw new RuntimeException("CSV書き込み失敗");
            }
        })
    }

}