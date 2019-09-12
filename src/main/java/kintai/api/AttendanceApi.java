package kintai.api;

import kintai.domain.*;
import kintai.service.AttendanceService;
import lombok.RequiredArgsConstructor;

import java.io.*;
import java.time.LocalDateTime;

@RequiredArgsConstructor
public class AttendanceApi {

    private final AttendanceService service;

    private final EmployeeRule employeeRule;
    public void input(String date, String start, String end, LocalDateTime now) {
        AttendanceInputRequest request = new AttendanceInputRequest(date, start, end);
        service.input(request.getEntity(), employeeRule, now);
    }

    public void total(String yearMonth, PrintStream out) {
        AttendanceTotalRequest request = new AttendanceTotalRequest(yearMonth);

        TotalWorkingTime totalWorkingTime = service.total(request.getEntity());

        out.println("勤務時間: " + totalWorkingTime.getWorkingDuration().toHours() + "時間" + totalWorkingTime.getWorkingDuration().getMinutesInHour() + "分");
        out.println("残業時間: " + totalWorkingTime.getOverWorkingDuration().toHours() + "時間" + totalWorkingTime.getOverWorkingDuration().getMinutesInHour() + "分");
    }
}
