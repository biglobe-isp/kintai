package com.naosim.dddwork.kintai_management.api.input;

import com.naosim.dddwork.kintai_management.api.form.RegistrationDateForm;
import com.naosim.dddwork.kintai_management.api.form.WorkingEndTimeForm;
import com.naosim.dddwork.kintai_management.api.form.WorkingStartTimeForm;
import com.naosim.dddwork.kintai_management.service.input.KintaiManagementRegistrationService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * 勤怠管理登録API
 */
@Controller
public class KintaiManagementRegistrationApi {

    @Autowired
    private KintaiManagementRegistrationRequest kintaiManagementRegistrationRequest;

    @Autowired
    private KintaiManagementRegistrationService kintaiManagementRegistrationService;

//    @Getter
//    private final static String URI = "/kintai-management-registration";

    public void main(String[] args) {

        try{

            if(args.length < 3) {
                throw new RuntimeException("引数が足りません");
            }

            kintaiManagementRegistrationRequest.setRegistrationDateForm(new RegistrationDateForm(args[0]));
            kintaiManagementRegistrationRequest.setWorkingStartTimeForm(new WorkingStartTimeForm(args[1]));
            kintaiManagementRegistrationRequest.setWorkingEndTimeForm(new WorkingEndTimeForm(args[2]));


            kintaiManagementRegistrationService.kintaiManagementRegistration(
                    kintaiManagementRegistrationRequest.makeKintaiManagementRegistrationInput()
            );

        } catch (Exception e) {
            e.printStackTrace();
        }

        return;
    }
}
