package jp.co.biglobe.isp.kintai.domain.entity;

import jp.co.biglobe.isp.kintai.domain.AttendanceYearMonth;
import jp.co.biglobe.isp.kintai.domain.DailyAttendancesOfMonth;

import java.time.YearMonth;

public record MonthlyAttendance(AttendanceYearMonth yearMonth, DailyAttendancesOfMonth dailyAttendancesOfMonth) {
}
