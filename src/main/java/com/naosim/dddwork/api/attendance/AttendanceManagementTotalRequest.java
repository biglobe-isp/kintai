package com.naosim.dddwork.api.attendance;

import com.naosim.dddwork.domain.attendance.TotalYearMonth;
import com.naosim.dddwork.domain.use_case.AttendanceTotalInquiry;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
public class AttendanceManagementTotalRequest {

    @Getter
    @Setter
    @NotNull
    @Valid
    private TotalYearMonth totalYearMonth;

    AttendanceTotalInquiry makeAttendanceTotalInquiry() {
        return new AttendanceTotalInquiry(
                this.getTotalYearMonth()
        );
    }
}
