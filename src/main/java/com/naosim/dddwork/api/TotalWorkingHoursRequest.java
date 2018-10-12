package com.naosim.dddwork.api;

import com.naosim.dddwork.domain.use_case.TotalWorkingHoursApplication;
import com.naosim.dddwork.domain.WorkingMonth;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
public class TotalWorkingHoursRequest {
    @Getter
    @Setter
    @NotNull
    @Valid
    private  WorkingMonth workingMonth;

    TotalWorkingHoursApplication makeTotalWorkingHoursApplication() {
        return new TotalWorkingHoursApplication(
                this.getWorkingMonth()
        );
    }


}
