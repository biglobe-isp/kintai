package jp.co.biglobe.isp.kintai.domain.rule;

import jp.co.biglobe.isp.kintai.domain.daily.AttendanceDuration;
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

    public static int calculateActualTotalBreakTime(AttendanceDuration duration) {
        return Arrays.stream(values())
                .mapToInt(offHour -> offHour.calcBreakTime(duration))
                .sum();
    }

    public int calcBreakTime(
            AttendanceDuration duration) {

        if (!containsBreak(duration)) return 0;

        return (int) ChronoUnit.MINUTES.between(
                duration.attendanceStartTime().max(startTime),
                duration.attendanceEndTime().min(endTime)
        );
    }

    public boolean containsBreak(AttendanceDuration duration) {
        return !(duration.attendanceStartTime().isAfter(endTime)
                || duration.attendanceEndTime().isBefore(startTime));
    }
}
