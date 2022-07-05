package jp.co.biglobe.isp.kintai.service;

import jp.co.biglobe.isp.kintai.domain.daily.AttendanceDate;
import jp.co.biglobe.isp.kintai.domain.daily.AttendanceDuration;
import jp.co.biglobe.isp.kintai.domain.daily.DailyAttendance;
import jp.co.biglobe.isp.kintai.domain.monthly.AttendanceYearMonth;
import jp.co.biglobe.isp.kintai.domain.monthly.DailyAttendancesOfMonth;
import jp.co.biglobe.isp.kintai.domain.monthly.TotalWorkedHoursResult;
import jp.co.biglobe.isp.kintai.domain.monthly.MonthlyAttendance;

import java.util.List;

public class AttendanceManagementService {
    private final DailyAttendanceRepository attendanceRepository;
    private final DailyAttendanceFactory dailyAttendanceFactory;

    public AttendanceManagementService(
            DailyAttendanceRepository attendanceRepository,
            DailyAttendanceFactory dailyAttendanceFactory) {
        this.attendanceRepository = attendanceRepository;
        this.dailyAttendanceFactory = dailyAttendanceFactory;
    }

    public void inputAttendance(
            AttendanceDate attendanceDate,
            AttendanceDuration attendanceDuration) {
        attendanceRepository.persist(
                dailyAttendanceFactory.create(
                        attendanceDate,
                        attendanceDuration
                ));
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
