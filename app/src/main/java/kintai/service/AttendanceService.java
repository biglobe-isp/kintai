package kintai.service;

import kintai.domain.Attendance;
import kintai.domain.AttendanceDate;
import kintai.domain.AttendanceRepository;
import kintai.domain.AttendanceTime;
import kintai.domain.OverWorkDuration;
import kintai.domain.WorkDuration;
import lombok.RequiredArgsConstructor;

import java.time.YearMonth;

@RequiredArgsConstructor
public class AttendanceService {

    private final AttendanceRepository attendanceRepository;

    /**
     * 勤怠入力.
     *
     * @param request 勤怠入力リクエスト
     */
    public void input(AttendanceInputRequest request) {
        AttendanceTime attendanceTime = new AttendanceTime(
                request.getStart(), request.getEnd());
        WorkDuration workDuration = attendanceTime.calculateWorkDuration();
        OverWorkDuration overWorkDuration = workDuration.calculateOverWorkDuration();
        Attendance attendance = new Attendance(
                new AttendanceDate(request.getDate()),
                attendanceTime,workDuration,overWorkDuration);

        attendanceRepository.persist(attendance);
    }

    /**
     * 勤怠集計.
     *
     * @param yearMonth 年月
     * @return 勤怠集計レスポンス
     */
    public AttendanceTotalResponse total(String yearMonth) {
        long totalWorkMinutes = 0L;
        long totalOverWorkMinutes = 0L;

        for (Attendance attendance :
                attendanceRepository.select(
                        YearMonth.of(Integer.parseInt(yearMonth.substring(0,4)),
                                     Integer.parseInt(yearMonth.substring(4,6))))) {
            totalWorkMinutes += attendance.getWorkDuration().getDuration().toMinutes();
            totalOverWorkMinutes += attendance.getOverWorkDuration().getDuration().toMinutes();
        }
        return new AttendanceTotalResponse(totalWorkMinutes,totalOverWorkMinutes);
    }
}
