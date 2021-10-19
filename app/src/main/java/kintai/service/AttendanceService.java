package kintai.service;

import kintai.domain.Attendance;
import kintai.domain.AttendanceRepository;
import kintai.domain.AttendanceTime;
import kintai.domain.OverWorkDuration;
import kintai.domain.WorkDuration;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.List;

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
                LocalDateTime.parse(request.getStart()),
                LocalDateTime.parse(request.getEnd()));
        WorkDuration workDuration = attendanceTime.calculateWorkDuration();
        OverWorkDuration overWorkDuration = workDuration.calculateOverWorkDuration();
        Attendance attendance = new Attendance(
                LocalDate.parse(request.getDate()),
                attendanceTime,workDuration,overWorkDuration);

        attendanceRepository.persist(attendance);
    }

    /**
     * 勤怠集計.
     *
     * @param yearMonth 年月
     * @return 勤怠集計レスポンス
     */
    public AttendanceTotalResponse total(YearMonth yearMonth) {
        long totalWorkMinutes = 0L;
        long totalOverWorkMinutes = 0L;

        for (Attendance attendance : attendanceRepository.select(yearMonth)) {
            totalWorkMinutes += attendance.getWorkDuration().getDuration().toMinutes();
            totalOverWorkMinutes += attendance.getOverWorkDuration().getDuration().toMinutes();
        }
        return new AttendanceTotalResponse(totalWorkMinutes,totalOverWorkMinutes);
    }
}
