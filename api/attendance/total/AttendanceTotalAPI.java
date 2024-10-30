package com.naosim.dddwork.api.attendance.total;

import com.naosim.dddwork.domain.attendance.total.AttendanceTotalData;
import com.naosim.dddwork.service.attendance.total.AttendanceTotalService;

public class AttendanceTotalAPI {
    private final AttendanceTotalService service = new AttendanceTotalService();

    public AttendanceTotalResponse get(String[] args) {
        AttendanceTotalResponse response = new AttendanceTotalResponse();
        response.result_code = 200;
        response.result_msg = "OK";

        try {
            AttendanceTotalData attendanceTotalData = service.getMonthlyTotal();
            response.workMinutesSum = attendanceTotalData.getWorkTimeMinutesSum();
            response.overWorkMinutesSum = attendanceTotalData.getOverWorkTimeMinutesSum();
        } catch (Exception e) {
            response.result_code = 400;
            response.result_msg = e.getMessage();
        }

        return response;
    }
}
