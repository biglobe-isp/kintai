package com.naosim.dddwork.domain.attendance;

public interface AttendanceRepository {

    void inputAttendant();

    AttendanceDetail findAttendant();
}
