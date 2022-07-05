package jp.co.biglobe.isp.kintai.service;

import jp.co.biglobe.isp.kintai.domain.daily.DailyAttendance;
import jp.co.biglobe.isp.kintai.domain.monthly.AttendanceYearMonth;

import java.util.List;

public interface DailyAttendanceRepository {
    void persist(DailyAttendance dailyAttendance);
    List<DailyAttendance> findByAttendanceYearMonth(AttendanceYearMonth attendanceYearMonth);
}
