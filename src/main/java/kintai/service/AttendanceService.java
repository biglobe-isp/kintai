package kintai.service;

import kintai.domain.*;

import java.time.LocalDateTime;

public class AttendanceService {

    private final AttendanceRepository repository;

    public AttendanceService(AttendanceRepository repository) {
        this.repository = repository;
    }

    public Attendance input(AttendanceStartAndEndTime attendanceStartAndEndTime, EmployeeRule employeeRule,
                            LocalDateTime now) {
        Attendance attendance = attendanceStartAndEndTime.calculateTotalWorkingTime(employeeRule, now);
        repository.saveAttendance(attendance);
        return attendance;
    }

    public TotalWorkingTime total(AttendanceMonth month) {
        Attendances attendances = repository.findAttendancesByMonth(month);
        return attendances.totalWorkingTime();
    }
}
