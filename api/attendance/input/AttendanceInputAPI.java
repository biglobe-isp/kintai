package com.naosim.dddwork.api.attendance.input;

import com.naosim.dddwork.domain.attendance.input.WorkDate;
import com.naosim.dddwork.domain.attendance.input.WorkEndTime;
import com.naosim.dddwork.domain.attendance.input.WorkStartTime;
import com.naosim.dddwork.service.attendance.input.AttendanceInputService;

import java.time.LocalDate;
import java.time.LocalTime;

public class AttendanceInputAPI {
    private final AttendanceInputService service = new AttendanceInputService();

    public AttendanceInputResponse get(String[] args) {
        AttendanceInputResponse response = new AttendanceInputResponse();
        response.result_code = 200;
        response.result_msg = "OK";

        try {
            AttendanceInputRequest request = parseRequestDataToDTO(args);

            service.input(
                    new WorkDate(request.date),
                    new WorkStartTime(request.startTime),
                    new WorkEndTime(request.endTime)
            );
        } catch (Exception e) {
            response.result_code = 400;
            response.result_msg = e.getMessage();
        }

        return response;
    }

    //SpringでやってくれるリクエストパラメータのParsingを担当
    private AttendanceInputRequest parseRequestDataToDTO(String[] args) {
        try {
            LocalDate date = LocalDate.of(
                    Integer.parseInt(args[0].substring(0, 4)),
                    Integer.parseInt(args[0].substring(4, 6)),
                    Integer.parseInt(args[0].substring(6, 8))
            );
            LocalTime startTime = LocalTime.of(
                    Integer.parseInt(args[1].substring(0, 2)),
                    Integer.parseInt(args[1].substring(2, 4))
            );
            LocalTime endTime = LocalTime.of(
                    Integer.parseInt(args[2].substring(0, 2)),
                    Integer.parseInt(args[2].substring(2, 4))
            );

            return new AttendanceInputRequest(date, startTime, endTime);
        } catch (Exception e) {
            throw new RuntimeException("リクエストパラメータが正しくない形式で渡されました。");
        }
    }
}
