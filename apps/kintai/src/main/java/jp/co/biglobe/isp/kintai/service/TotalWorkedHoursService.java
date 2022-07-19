package jp.co.biglobe.isp.kintai.service;

import jp.co.biglobe.isp.kintai.domain.daily.DailyAttendance;
import jp.co.biglobe.isp.kintai.domain.monthly.AttendanceYearMonth;
import jp.co.biglobe.isp.kintai.domain.monthly.DailyAttendancesOfMonth;
import jp.co.biglobe.isp.kintai.domain.monthly.MonthlyAttendance;
import jp.co.biglobe.isp.kintai.domain.monthly.TotalWorkedHoursResult;

import java.util.List;

public class TotalWorkedHoursService {
    private final DailyAttendanceRepository attendanceRepository;

    public TotalWorkedHoursService(
            DailyAttendanceRepository attendanceRepository) {
        this.attendanceRepository = attendanceRepository;
    }

    public TotalWorkedHoursResult totalWorkingHours(AttendanceYearMonth attendanceYearMonth) {
        final List<DailyAttendance> dailyAttendanceList = attendanceRepository.findByAttendanceYearMonth(
                attendanceYearMonth);

        final MonthlyAttendance monthlyAttendance =
                new MonthlyAttendance(
                        attendanceYearMonth,
                        new DailyAttendancesOfMonth(dailyAttendanceList)
                );

        return monthlyAttendance.totalWorkingHours();
    }
}
