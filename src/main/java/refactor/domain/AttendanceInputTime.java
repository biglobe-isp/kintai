package refactor.domain;

import lombok.NonNull;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AttendanceInputTime {
    private final String attendanceInputTime;
    private final static String ATTENDANCE_INPUT_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS";

    public AttendanceInputTime() {
        attendanceInputTime = LocalDateTime.now().toString();
    }

    public AttendanceInputTime(@NonNull String attendanceInputTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(ATTENDANCE_INPUT_TIME_FORMAT);
        this.attendanceInputTime = LocalDateTime.parse(attendanceInputTime, formatter).toString();
    }

    public String now() {
        return attendanceInputTime;
    }
}
