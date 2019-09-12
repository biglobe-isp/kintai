package kintai.datasource

import kintai.domain.Attendance
import kintai.domain.AttendanceMonth
import kintai.domain.EndTime
import kintai.domain.StartTime
import kintai.domain.WorkingDuration
import spock.lang.Specification

import java.nio.file.Files
import java.time.Duration
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.util.stream.Collectors

class AttendanceRepositoryCsvSpec extends Specification {
    def "勤怠記録をCSVファイルに記録する"() {
        setup:
        def tempPath = Files.createTempFile("temp", this.class.toString())
        def now = LocalDateTime.of(LocalDate.of(2019, 9, 10), LocalTime.of(12, 34))

        def attendanceDate = LocalDate.of(2019, 9, 2)
        def startTime = StartTime.of(9, 0);
        def endTime = EndTime.of(20, 0);
        def workingDuration = WorkingDuration.ofMinutes(540)
        def overWorkingDuration = WorkingDuration.ofMinutes(60)
        def attendance = new Attendance(attendanceDate, startTime, endTime, workingDuration, overWorkingDuration, now)

        def repository = new AttendanceRepositoryCsv(tempPath)

        def expected = Arrays.asList("20190902,0900,2000,540,60,2019-09-10T12:34")

        when:
        repository.saveAttendance(attendance)

        then:
        Files.readAllLines(tempPath) == expected
    }

    def "9月の勤怠をCSVファイルから取得する"() {
        setup:
        def tempPath = Files.createTempFile("temp", this.class.toString())
        Files.write(tempPath, Arrays.asList(
                "20190830,0900,2000,540,60,2019-09-10T12:34",
                "20190902,0900,2000,540,60,2019-09-10T12:35",
                "20190902,0900,2000,540,60,2019-09-10T12:36",
                "20190903,0900,2000,540,60,2019-09-10T12:37",
                "20191001,0900,2000,540,60,2019-09-10T12:38"
        ))
        // 勤怠記録全体の expected を書くのが大変なので、指定の勤怠入力日のレコードが取得できていることを確認する
        def expected = Arrays.asList(
                LocalDateTime.parse("2019-09-10T12:36"),  // 勤怠は複数登録可 & 上書き（後勝ち）のため12:36の方のみ取得
                LocalDateTime.parse("2019-09-10T12:37")
        )

        def repository = new AttendanceRepositoryCsv(tempPath)

        when:
        def result = repository.findAttendancesByMonth(AttendanceMonth.of(2019, 9))

        then:
        def inputDates = result.values.stream().map({ a -> a.getInputDateTime() }).sorted().collect(Collectors.toList())
        inputDates == expected
    }
}
