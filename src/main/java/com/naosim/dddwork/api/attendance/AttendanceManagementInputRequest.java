package com.naosim.dddwork.api.attendance;

import com.naosim.dddwork.domain.attendance.EndTime;
import com.naosim.dddwork.domain.attendance.StartTime;
import com.naosim.dddwork.domain.attendance.WorkDate;
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
    private WorkDate workDate;

    @Getter
    @Setter
    @NotNull
    @Valid
    private StartTime startTime;

    @Getter
    @Setter
    @NotNull
    @Valid
    private EndTime endTime;

    AttendanceInputApplication makeAttendanceInputApplication() {
        return new AttendanceInputApplication(
                this.getWorkDate(),
                this.getStartTime(),
                this.getEndTime()
        );
    }
}
