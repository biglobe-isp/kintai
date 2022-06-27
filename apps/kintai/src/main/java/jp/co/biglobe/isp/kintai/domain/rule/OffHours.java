package jp.co.biglobe.isp.kintai.domain.rule;

import com.google.common.collect.Comparators;
import jp.co.biglobe.isp.kintai.domain.daily.AttendanceEndTime;
import jp.co.biglobe.isp.kintai.domain.daily.AttendanceStartTime;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;

public enum OffHours {
    /**
     * 昼休み
     */
    LUNCHTIME(LocalTime.of(12, 00), LocalTime.of(13, 00)),
    /**
     * 夕方休憩
     */
    EVENING(LocalTime.of(18, 00), LocalTime.of(19, 00)),
    /**
     * 深夜休憩
     */
    MIDNIGHT(LocalTime.of(21, 00), LocalTime.of(22, 00)),
    ;
    final LocalTime startTime;
    final LocalTime endTime;

    OffHours(LocalTime startTime, LocalTime endTime) {
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
                Comparators.max(attendanceStartTime.value(), startTime),
                Comparators.min(attendanceEndTime.value(), endTime)
        )
                : 0;
    }

    public boolean containsBreak(AttendanceStartTime attendanceStartTime, AttendanceEndTime attendanceEndTime) {
        return !(attendanceStartTime.value().isAfter(endTime)
                || attendanceEndTime.value().isBefore(startTime));
    }
}
