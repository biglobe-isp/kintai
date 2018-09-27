package com.naosim.dddwork.service.attendance;

import com.naosim.dddwork.domain.attendance.Attendance;
import com.naosim.dddwork.domain.attendance.AttendanceRepository;
import com.naosim.dddwork.domain.use_case.AttendanceInputApplication;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 勤怠管理入力Service
 */
@Service
@NoArgsConstructor
public class AttendanceInputService {

    @Autowired
    private AttendanceRepository attendanceRepository;

    public void input(AttendanceInputApplication attendanceInputApplication) {

        // 勤怠の永続化
        attendanceRepository.input(
                Attendance.create(
                        attendanceInputApplication.getWorkDate(),
                        attendanceInputApplication.getStartTime(),
                        attendanceInputApplication.getEndTime()
                )
        );
    }
}
