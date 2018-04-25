package com.naosim.dddwork.kintai_management.domain.duty.input;

import com.naosim.dddwork.kintai_management.domain.word.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 *
 */
@AllArgsConstructor
@Getter
public class WorkingTimeRegistrationInput {

    private RegistrationDate registrationDate;

    private WorkingStartTime workingStartTime;

    private WorkingEndTime workingEndTime;

    private WorkingTime workingTime;

    private OverWorkingTime overWorkingTime;

}
