package apps.kintai.src.main.java.jp.co.biglobe.isp.kintai.domain.entity;

import apps.kintai.src.main.java.jp.co.biglobe.isp.kintai.domain.AttendanceDate;
import apps.kintai.src.main.java.jp.co.biglobe.isp.kintai.domain.AttendanceTime;
import apps.kintai.src.main.java.jp.co.biglobe.isp.kintai.domain.OvertimeHours;
import apps.kintai.src.main.java.jp.co.biglobe.isp.kintai.domain.WorktimeHours;

public record DailyAttendance(AttendanceDate attendanceDate, AttendanceTime startTime, AttendanceTime endTime) {
    public WorktimeHours calcWorkedTimeHours() {
        return new WorktimeHours(this);
    }

    public OvertimeHours calcOverTimeHours() {
        return new OvertimeHours(this);
    }
}
