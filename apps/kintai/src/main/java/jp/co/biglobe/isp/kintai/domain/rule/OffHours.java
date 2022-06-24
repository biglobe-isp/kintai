package jp.co.biglobe.isp.kintai.domain.rule;

import com.google.common.collect.Comparators;
import jp.co.biglobe.isp.kintai.domain.AttendanceEndTime;
import jp.co.biglobe.isp.kintai.domain.AttendanceStartTime;

import java.time.temporal.ChronoUnit;
import java.util.Arrays;

public enum OffHours {
    /**
     * 昼休み
     */
    LUNCHTIME(AttendanceStartTime.of("1200"), AttendanceEndTime.of("1300")),
    /**
     * 夕方休憩
     */
    EVENING(AttendanceStartTime.of("1800"), AttendanceEndTime.of("1900")),
    /**
     * 深夜休憩
     */
    MIDNIGHT(AttendanceStartTime.of("2100"), AttendanceEndTime.of("2200")),
    ;
    final AttendanceStartTime startTime;
    final AttendanceEndTime endTime;

    OffHours(AttendanceStartTime startTime, AttendanceEndTime endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public static int calcActualTotalBreak(
            AttendanceStartTime attendanceStartTime,
            AttendanceEndTime attendanceEndTime,
            ChronoUnit chronoUnit) {
        return Arrays.stream(values())
                .mapToInt(offHour -> offHour.calcBreakTime(attendanceStartTime, attendanceEndTime, chronoUnit))
                .sum();
    }

    public int calcBreakTime(
            AttendanceStartTime attendanceStartTime,
            AttendanceEndTime attendanceEndTime,
            ChronoUnit chronoUnit) {
        return containsBreak(attendanceStartTime, attendanceEndTime)
                ? (int) chronoUnit.between(
                Comparators.max(attendanceStartTime.value(), startTime.value()),
                Comparators.min(attendanceEndTime.value(), endTime.value())
        )
                : 0;
    }

    public boolean containsBreak(AttendanceStartTime attendanceStartTime, AttendanceEndTime attendanceEndTime) {
        return !(attendanceStartTime.value().isAfter(endTime.value())
                || attendanceEndTime.value().isBefore(startTime.value()));
    }
}
