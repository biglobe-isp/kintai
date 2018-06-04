package com.naosim.dddwork.kintai_management.service.regist;

import com.naosim.dddwork.kintai_management.domain.duty.regist.WorkingTimeDataInput;
import com.naosim.dddwork.kintai_management.domain.word.HolidayKind;
import com.naosim.dddwork.kintai_management.domain.word.RegistrationDate;
import com.naosim.dddwork.kintai_management.domain.word.WorkingEndTime;
import com.naosim.dddwork.kintai_management.domain.word.WorkingStartTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

/**
 * 勤怠管理登録サービス入力情報
 */
@AllArgsConstructor
@Getter
public class KintaiManagementRegistServiceInput {

    @NonNull
    private RegistrationDate registrationDate;

    @NonNull
    private WorkingStartTime workingStartTime;

    @NonNull
    private WorkingEndTime workingEndTime;

    @NonNull
    private HolidayKind holidayKind;

    public WorkingTimeDataInput makeWorkingTimeRegistInput() {
        return new WorkingTimeDataInput(
                registrationDate,
                workingStartTime,
                workingEndTime,
                holidayKind
                );
    }
}