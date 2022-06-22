package jp.co.biglobe.isp.kintai.domain.rule;

import jp.co.biglobe.isp.kintai.domain.AttendanceEndTime;
import jp.co.biglobe.isp.kintai.domain.AttendanceStartTime;

public enum OffHours {
    /** 昼休み */ 
    LUNCHTIME(AttendanceStartTime.of("1200"), AttendanceEndTime.of("1300")),
    /** 夕方休憩 */
    EVENING(AttendanceStartTime.of("1800"), AttendanceEndTime.of("1900")),
    /** 深夜休憩 */
    MIDNIGHT(AttendanceStartTime.of("2100"), AttendanceEndTime.of("2200")),
    ;
    final AttendanceStartTime startTime;
    final AttendanceEndTime endTime;

    OffHours(AttendanceStartTime startTime, AttendanceEndTime endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public AttendanceStartTime getStartTime() {
        return startTime;
    }

    public AttendanceEndTime getEndTime() {
        return endTime;
    }
}
