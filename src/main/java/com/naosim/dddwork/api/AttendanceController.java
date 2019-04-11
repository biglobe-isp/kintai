package com.naosim.dddwork.api;

import com.naosim.dddwork.domain.AttendanceSummary;
import com.naosim.dddwork.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

@Controller
public class AttendanceController {

    private final AttendanceService attendanceService;

    @Autowired
    public AttendanceController(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }

    public void command(String[] args) {
        // TODO: Not Implemented
        throw new NotImplementedException();
    }

    private void input(String date, String start, String end) {
        // TODO: Not Implemented
        throw new NotImplementedException();
    }

    private AttendanceSummary total(String yearMonth) {
        // TODO: Not Implemented
        throw new NotImplementedException();
    }
}
