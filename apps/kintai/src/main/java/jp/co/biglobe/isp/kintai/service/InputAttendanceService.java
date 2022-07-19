package jp.co.biglobe.isp.kintai.service;

import jp.co.biglobe.isp.kintai.domain.daily.AttendanceDate;
import jp.co.biglobe.isp.kintai.domain.daily.AttendanceDuration;
import jp.co.biglobe.isp.kintai.domain.daily.DailyAttendanceFactory;

public class InputAttendanceService {
    private final DailyAttendanceRepository attendanceRepository;
    private final DailyAttendanceFactory dailyAttendanceFactory;

    public InputAttendanceService(
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
}
