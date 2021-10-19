package kintai.api;

import kintai.domain.AttendanceRepository;

public class AttendanceApi {

    private final AttendanceRepository attendanceRepository;

    public AttendanceApi(AttendanceRepository attendanceRepository) {
        this.attendanceRepository = attendanceRepository;
    }

    public String get() {
        return "apiが呼び出せた";
    }

}
