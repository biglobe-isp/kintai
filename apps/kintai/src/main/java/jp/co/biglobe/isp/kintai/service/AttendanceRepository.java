package jp.co.biglobe.isp.kintai.service;

import jp.co.biglobe.isp.kintai.domain.AttendanceYearMonth;
import jp.co.biglobe.isp.kintai.domain.entity.DailyAttendance;
import jp.co.biglobe.isp.kintai.domain.entity.MonthlyAttendance;

import java.io.IOException;
import java.util.Optional;

public interface AttendanceRepository {
    void persist(DailyAttendance dailyAttendance);
    Optional<MonthlyAttendance> findMonthlyAttendance(AttendanceYearMonth attendanceYearMonth);
}
