package jp.co.biglobe.isp.kintai.domain.daily;

import jp.co.biglobe.isp.kintai.domain.rule.OffHours;

import java.time.temporal.ChronoUnit;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.IntFunction;

public record AttendanceDuration(AttendanceStartTime attendanceStartTime, AttendanceEndTime attendanceEndTime) {
    public AttendanceDuration {
        if (attendanceStartTime.isAfter(attendanceEndTime.value()))
            throw new IllegalArgumentException("勤怠開始時刻は勤怠終了時刻より前の時刻を入力してください。");
    }

    public WorkTimeMinutes calculateWorkTimeMinutes(Function<AttendanceDuration, Integer> breakTimeCalculator) {
        final int attendanceTimeMinutes = (int) ChronoUnit.MINUTES.between(
                attendanceStartTime.value(),
                attendanceEndTime.value()
        );
        final int actualTotalBreakMinutes = breakTimeCalculator.apply(this);
        return new WorkTimeMinutes(attendanceTimeMinutes - actualTotalBreakMinutes);
    }
}
