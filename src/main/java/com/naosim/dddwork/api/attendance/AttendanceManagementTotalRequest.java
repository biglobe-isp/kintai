package com.naosim.dddwork.api.attendance;

import com.naosim.dddwork.api.form.TotalYearMonthForm;
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
    private TotalYearMonthForm totalYearMonthForm;

    AttendanceTotalInquiry makeAttendanceTotalInquiry() {
        return new AttendanceTotalInquiry(
                this.getTotalYearMonthForm().getValueObject()
        );
    }
}
