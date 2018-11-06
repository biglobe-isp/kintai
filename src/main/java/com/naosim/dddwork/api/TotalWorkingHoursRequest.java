package com.naosim.dddwork.api;

import com.naosim.dddwork.domain.WorkingMonth;
import com.naosim.dddwork.domain.use_case.TotalWorkingHoursApplication;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class TotalWorkingHoursRequest {
    @Getter
    @Setter
    @NotNull
    @Valid
    private WorkingMonth workingMonth;

    TotalWorkingHoursApplication makeTotalWorkingHoursApplication() {
        return new TotalWorkingHoursApplication(
                this.getWorkingMonth()
        );
    }


}
