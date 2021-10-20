package kintai.api;

import kintai.service.AttendanceInputRequest;
import kintai.service.AttendanceService;
import kintai.service.AttendanceTotalResponse;

import java.time.YearMonth;

public class AttendanceApi {

    private final AttendanceService attendanceService;

    public AttendanceApi(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }

    /**
     * 勤怠入力.
     *
     * @param request 勤怠入力リクエスト
     */
    public void input(AttendanceInputRequest request) {
        attendanceService.input(request);
    }

    /**
     * 勤怠集計.
     *
     * @param yearMonth 年月
     * @return 勤怠集計レスポンス
     */
    public AttendanceTotalResponse total(YearMonth yearMonth) {
        return attendanceService.total(yearMonth);
    }

}
