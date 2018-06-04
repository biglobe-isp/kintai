package com.naosim.dddwork.kintai_management.api.regist;

import com.naosim.dddwork.kintai_management.api.form.HolidayKindForm;
import com.naosim.dddwork.kintai_management.api.form.RegistrationDateForm;
import com.naosim.dddwork.kintai_management.api.form.WorkingEndTimeForm;
import com.naosim.dddwork.kintai_management.api.form.WorkingStartTimeForm;
import com.naosim.dddwork.kintai_management.service.regist.KintaiManagementRegistServiceInput;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import javax.validation.constraints.NotNull;

/**
 * 勤怠管理登録リクエスト情報
 */
@Component
public class KintaiManagementRegistRequest {

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

    public void checkRequestArgs() {

        if(args.length < 3) {
            throw new RuntimeException("引数指定の誤り：引数が足りません");
        }

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
    }

    public KintaiManagementRegistServiceInput makeKintaiManagementRegistServiceInput() {

        return new KintaiManagementRegistServiceInput(
                this.getRegistrationDateForm().getValueObject(),
                this.getWorkingStartTimeForm().getValueObject(),
                this.getWorkingEndTimeForm().getValueObject(),
                this.getHolidayKindForm().getValueObject()
        );
    }
}
