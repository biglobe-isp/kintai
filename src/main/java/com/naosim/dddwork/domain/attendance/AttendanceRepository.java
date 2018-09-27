package com.naosim.dddwork.domain.attendance;

import java.util.Optional;

public interface AttendanceRepository {

    void input(Attendance attendance);

    Optional<AttendanceList> find();
}
