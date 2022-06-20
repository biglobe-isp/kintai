package apps.kintai.src.main.java.jp.co.biglobe.isp.kintai.domain.rule;

import apps.kintai.src.main.java.jp.co.biglobe.isp.kintai.domain.AttendanceTime;

public enum OffHours {
    /** 昼休み */ 
    LUNCHTIME(AttendanceTime.of("1200"), AttendanceTime.of("1300")),
    /** 夕方休憩 */
    EVENING(AttendanceTime.of("1800"), AttendanceTime.of("1900")),
    /** 深夜休憩 */
    MIDNIGHT(AttendanceTime.of("2100"), AttendanceTime.of("2200")),
    ;
    final AttendanceTime startTime;
    final AttendanceTime endTime;

    OffHours(AttendanceTime startTime, AttendanceTime endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public AttendanceTime getStartTime() {
        return startTime;
    }

    public AttendanceTime getEndTime() {
        return endTime;
    }
}
