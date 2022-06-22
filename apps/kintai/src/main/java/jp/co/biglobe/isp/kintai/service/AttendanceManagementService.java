package jp.co.biglobe.isp.kintai.service;

import jp.co.biglobe.isp.kintai.domain.AttendanceYearMonth;
import jp.co.biglobe.isp.kintai.domain.TotalWorkedHoursResult;
import jp.co.biglobe.isp.kintai.domain.entity.DailyAttendance;
import jp.co.biglobe.isp.kintai.domain.entity.MonthlyAttendance;

import java.util.Optional;

public class AttendanceManagementService {
    private final AttendanceRepository attendanceRepository;

    public AttendanceManagementService(AttendanceRepository attendanceRepository) {
        this.attendanceRepository = attendanceRepository;
    }

    void inputAttendance(DailyAttendance dailyAttendance) {
        attendanceRepository.persist(dailyAttendance);
    }

    TotalWorkedHoursResult totalWorkingHours(AttendanceYearMonth attendanceYearMonth) {
        Optional<MonthlyAttendance> monthlyAttendance = attendanceRepository.findMonthlyAttendance(attendanceYearMonth);

        // TODO 集計結果を返す
        return null;
    }
}