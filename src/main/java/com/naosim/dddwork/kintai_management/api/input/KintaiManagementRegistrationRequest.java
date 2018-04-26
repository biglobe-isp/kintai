package com.naosim.dddwork.kintai_management.api.input;


import com.naosim.dddwork.kintai_management.api.form.HolidayKindForm;
import com.naosim.dddwork.kintai_management.api.form.RegistrationDateForm;
import com.naosim.dddwork.kintai_management.api.form.WorkingEndTimeForm;
import com.naosim.dddwork.kintai_management.api.form.WorkingStartTimeForm;
import com.naosim.dddwork.kintai_management.domain.word.HolidayKind;
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
    private String[] args;

    @Getter
    @Setter
    private WorkingStartTimeForm workingStartTimeForm = new WorkingStartTimeForm(null);

    @Getter
    @Setter
    private WorkingEndTimeForm workingEndTimeForm = new WorkingEndTimeForm(null);

    @Getter
    @Setter
    @NotNull
    private RegistrationDateForm registrationDateForm;

    @Getter
    @Setter
    private HolidayKindForm holidayKindForm = new HolidayKindForm(null);

    public KintaiManagementRegistrationInput makeKintaiManagementRegistrationInput() {
        return new KintaiManagementRegistrationInput(
                this.getRegistrationDateForm().getValueObject(),
                this.getWorkingStartTimeForm().getValueObject(),
                this.getWorkingEndTimeForm().getValueObject(),
                this.getHolidayKindForm().getValueObject()
        );
    }

    public KintaiManagementRegistrationInput makeKintaiManagementRegistrationInputArgs() {

        for (String arg : args) {
            if(arg != null) {
                String[] param = arg.split(":");
                if ("-date".equals(param[0])) {
                    this.setRegistrationDateForm(new RegistrationDateForm(param[1]));
                } else if ("-start".equals(param[0])) {
                    this.setWorkingStartTimeForm(new WorkingStartTimeForm(param[1]));
                } else if ("-end".equals(param[0])) {
                    this.setWorkingEndTimeForm(new WorkingEndTimeForm(param[1]));
                } else if ("v".equals(param[0]) || "am".equals(param[0]) || "pm".equals(param[0])) {
                    this.setHolidayKindForm(new HolidayKindForm(param[0]));
                } else {
                    throw new RuntimeException("引数指定の誤り：未知の引数が指定されました");
                }
            }
        }

        return new KintaiManagementRegistrationInput(
                this.getRegistrationDateForm().getValueObject(),
                this.getWorkingStartTimeForm().getValueObject(),
                this.getWorkingEndTimeForm().getValueObject(),
                this.getHolidayKindForm().getValueObject()
        );
    }
}
