package apps.kintai.src.main.java.jp.co.biglobe.isp.kintai.service;

import apps.kintai.src.main.java.jp.co.biglobe.isp.kintai.domain.AttendanceYearMonth;
import apps.kintai.src.main.java.jp.co.biglobe.isp.kintai.domain.entity.DailyAttendance;
import apps.kintai.src.main.java.jp.co.biglobe.isp.kintai.domain.entity.MonthlyAttendance;

import java.util.Optional;

public interface AttendanceRepository {
    void persist(DailyAttendance dailyAttendance);
    Optional<MonthlyAttendance> findMonthlyAttendance(AttendanceYearMonth attendanceYearMonth);
}
