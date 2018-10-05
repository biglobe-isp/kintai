package com.naosim.dddwork.api.attendance;

import com.naosim.dddwork.api.form.EndTimeForm;
import com.naosim.dddwork.api.form.StartTimeForm;
import com.naosim.dddwork.api.form.WorkDateForm;
import com.naosim.dddwork.domain.use_case.AttendanceInputApplication;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
public class AttendanceManagementInputRequest {

    @Getter
    @Setter
    @NotNull
    @Valid
    private WorkDateForm workDateForm;

    @Getter
    @Setter
    @NotNull
    @Valid
    private StartTimeForm startTimeForm;

    @Getter
    @Setter
    @NotNull
    @Valid
    private EndTimeForm endTimeForm;

    AttendanceInputApplication makeAttendanceInputApplication() {
        return new AttendanceInputApplication(
                this.getWorkDateForm().getValueObject(),
                this.getStartTimeForm().getValueObject(),
                this.getEndTimeForm().getValueObject()
        );
    }
}
