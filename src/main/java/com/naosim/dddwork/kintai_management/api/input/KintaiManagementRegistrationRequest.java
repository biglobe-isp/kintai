package com.naosim.dddwork.kintai_management.api.input;


import com.naosim.dddwork.kintai_management.api.form.RegistrationDateForm;
import com.naosim.dddwork.kintai_management.api.form.WorkingEndTimeForm;
import com.naosim.dddwork.kintai_management.api.form.WorkingStartTimeForm;
import com.naosim.dddwork.kintai_management.service.input.KintaiManagementRegistrationInput;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * 勤怠管理登録リクエスト
 */
@Component
public class KintaiManagementRegistrationRequest {

    @Getter
    @Setter
    @NotNull
    @Valid
    private WorkingStartTimeForm workingStartTimeForm;

    @Getter
    @Setter
    @NotNull
    @Valid
    private WorkingEndTimeForm workingEndTimeForm;

    @Getter
    @Setter
    @NotNull
    @Valid
    private RegistrationDateForm registrationDateForm;

//    @Getter
//    @Setter
//    @Valid
//    private HolidayKindForm holidayKindForm = new HolidayKindForm(HolidayKind.NONE.getApiValue());

    public KintaiManagementRegistrationInput makeKintaiManagementRegistrationInput() {
        return new KintaiManagementRegistrationInput(
                this.getRegistrationDateForm().getValueObject(),
                this.getWorkingStartTimeForm().getValueObject(),
                this.getWorkingEndTimeForm().getValueObject()
//                this.getHolidayKindForm().getValueObject()
        );
    }
}
