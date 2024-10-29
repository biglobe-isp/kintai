package com.naosim.dddwork.service;

import com.naosim.dddwork.domain.service.AttendanceService;
import com.naosim.dddwork.domain.value_object.AttendanceTotalData;
import com.naosim.dddwork.service.dto.total.GetMonthlyTotalResponseDTO;

public class TotalService {
    private final AttendanceService attendanceService = new AttendanceService();

    public GetMonthlyTotalResponseDTO getMonthlyTotal() throws Exception {
        AttendanceTotalData data = attendanceService.getMonthlyTotal();

        return new GetMonthlyTotalResponseDTO(
                data.getWorkTimeMinutesSum(),
                data.getOverWorkTimeMinutesSum()
        );
    }
}
