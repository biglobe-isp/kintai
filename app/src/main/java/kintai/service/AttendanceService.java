package kintai.service;

import kintai.domain.Attendance;
import kintai.domain.AttendanceDate;
import kintai.domain.AttendanceRepository;
import kintai.domain.AttendanceTime;
import kintai.domain.OverWorkDuration;
import kintai.domain.WorkDuration;
import lombok.RequiredArgsConstructor;

import java.time.YearMonth;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public AttendanceTotalResponse total(YearMonth yearMonth) {
        long totalWorkMinutes = 0L;
        long totalOverWorkMinutes = 0L;

        Map<AttendanceDate, Attendance> attendanceMap = new HashMap<>();
        // 重複する日付の勤怠データのうち、最新のものに絞る
        attendanceRepository.select(yearMonth).forEach(attendance -> {
            attendanceMap.put(attendance.getAttendanceDate(),attendance);
        });

        for (Attendance attendance : attendanceMap.values()) {
            totalWorkMinutes += attendance.getWorkDuration().getDuration().toMinutes();
            totalOverWorkMinutes += attendance.getOverWorkDuration().getDuration().toMinutes();
        }
        return new AttendanceTotalResponse(totalWorkMinutes,totalOverWorkMinutes);
    }
}
