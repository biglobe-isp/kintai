package com.naosim.dddwork.kintai_management.service.input;

import com.naosim.dddwork.kintai_management.domain.duty.input.WorkingTimeRegistrationInput;
import com.naosim.dddwork.kintai_management.domain.word.*;
import lombok.*;

/**
 * 勤怠管理登録サービス入力情報
 */
@AllArgsConstructor
@Getter
public class KintaiManagementRegistrationInput {

    @NonNull
    private RegistrationDate registrationDate;

    @NonNull
    private WorkingStartTime workingStartTime;

    @NonNull
    private WorkingEndTime workingEndTime;

//    @NonNull
//    private HolidayKind holidayKind;

    public WorkingTimeRegistrationInput makeWorkingTimeRegistrationInput() {
        return new WorkingTimeRegistrationInput(
                registrationDate,
                workingStartTime,
                workingEndTime,
                WorkingTime.create(workingStartTime, workingEndTime),
                OverWorkingTime.create(workingStartTime, workingEndTime)
                );
    }
}