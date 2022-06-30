package jp.co.biglobe.isp.kintai.service;

import jp.co.biglobe.isp.kintai.domain.daily.DailyAttendance;
import jp.co.biglobe.isp.kintai.domain.monthly.AttendanceYearMonth;
import jp.co.biglobe.isp.kintai.domain.monthly.MonthlyAttendance;

import java.util.List;

public interface AttendanceRepository {
    void persist(DailyAttendance dailyAttendance);
    MonthlyAttendance findMonthlyAttendance(AttendanceYearMonth attendanceYearMonth);
}
