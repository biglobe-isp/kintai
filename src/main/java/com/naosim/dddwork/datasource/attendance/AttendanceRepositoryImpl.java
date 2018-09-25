package com.naosim.dddwork.datasource.attendance;

import com.naosim.dddwork.domain.attendance.AttendanceDetail;
import com.naosim.dddwork.domain.attendance.AttendanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class AttendanceRepositoryImpl implements AttendanceRepository {

    @Override
    public void inputAttendant() {

    }

    @Override
    public AttendanceDetail findAttendant() {

        return null;
    }
}
