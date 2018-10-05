package com.naosim.dddwork.domain.attendance;

import java.io.IOException;

public interface AttendanceRepository {

    void input(Attendance attendance) throws IOException;

    AttendanceHistory find() throws IOException;
}
