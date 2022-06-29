package jp.co.biglobe.isp.kintai.service;

import jp.co.biglobe.isp.kintai.domain.daily.AttendanceDate;
import jp.co.biglobe.isp.kintai.domain.daily.AttendanceEndTime;
import jp.co.biglobe.isp.kintai.domain.daily.AttendanceStartTime;
import jp.co.biglobe.isp.kintai.domain.daily.DailyAttendance;
import jp.co.biglobe.isp.kintai.domain.monthly.AttendanceYearMonth;
import jp.co.biglobe.isp.kintai.domain.monthly.TotalWorkedHoursResult;
import jp.co.biglobe.isp.kintai.domain.monthly.MonthlyAttendance;

import java.util.List;
import java.util.Optional;

public class AttendanceManagementService {
    private final AttendanceRepository attendanceRepository;
    private final DailyAttendanceFactory dailyAttendanceFactory;

    public AttendanceManagementService(
            AttendanceRepository attendanceRepository,
            DailyAttendanceFactory dailyAttendanceFactory) {
        this.attendanceRepository = attendanceRepository;
        this.dailyAttendanceFactory = dailyAttendanceFactory;
    }

    public void inputAttendance(
            AttendanceDate attendanceDate,
            AttendanceStartTime attendanceStartTime,
            AttendanceEndTime attendanceEndTime) {

        attendanceRepository.persist(
                dailyAttendanceFactory.create(
                        attendanceDate,
                        attendanceStartTime,
                        attendanceEndTime
                ));
    }

    public TotalWorkedHoursResult totalWorkingHours(AttendanceYearMonth attendanceYearMonth) {
        List<DailyAttendance> dailyAttendanceList = attendanceRepository.findMonthlyAttendance(
                attendanceYearMonth);

        // TODO 集計結果を返す
        return null;
    }
}
