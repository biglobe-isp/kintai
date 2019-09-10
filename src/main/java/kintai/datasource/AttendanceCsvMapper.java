package kintai.datasource;

import kintai.domain.Attendance;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class AttendanceCsvMapper {

    private static final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyyMMdd");
    private static final DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HHmm");
    private static final DateTimeFormatter timeFormatWithSecond = DateTimeFormatter.ofPattern("HHmmss");

    public String toCsvRecord(Attendance attendance) {
        return String.format("%s,%s,%s,%s,%s,%s\n",
                attendance.getAttendanceDate().format(dateFormat),
                attendance.getStartTime().format(timeFormat),
                attendance.getEndTime().format(timeFormat),
                attendance.getWorkingDuration().toMinutes(),
                attendance.getOverWorkingDuration().toMinutes(),
                attendance.getInputDateTime());
    }

    public Attendance fromCsvRecord(String record) {
        String[] columns = record.split(",");
        return new Attendance(
                LocalDate.parse(columns[0], dateFormat),
                LocalTime.parse(columns[1]+"00", timeFormatWithSecond),
                LocalTime.parse(columns[2]+"00", timeFormatWithSecond),
                Duration.ofMinutes(Long.parseLong(columns[3])),
                Duration.ofMinutes(Long.parseLong(columns[4])),
                LocalDateTime.parse(columns[5])
        );
    }
}
