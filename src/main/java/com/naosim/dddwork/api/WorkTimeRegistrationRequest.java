package com.naosim.dddwork.api;

import com.naosim.dddwork.domain.ClosingHours;
import com.naosim.dddwork.domain.StartingHours;
import com.naosim.dddwork.domain.WorkDay;
import com.naosim.dddwork.domain.use_case.WorkTimeRegistrationApplication;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class WorkTimeRegistrationRequest {
    @Getter
    @Setter
    @NotNull
    @Valid
    private WorkDay workDay;

    @Getter
    @Setter
    @NotNull
    @Valid
    private StartingHours startingHours;

    @Getter
    @Setter
    @NotNull
    @Valid
    private ClosingHours closingHours;

    WorkTimeRegistrationApplication makeWorkTimeRegistrationApplication() {
        return new WorkTimeRegistrationApplication(
                this.getWorkDay(),
                this.getStartingHours(),
                this.getClosingHours()
        );
    }
}
