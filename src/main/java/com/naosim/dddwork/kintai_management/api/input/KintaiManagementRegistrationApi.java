package com.naosim.dddwork.kintai_management.api.input;

import com.naosim.dddwork.kintai_management.service.input.KintaiManagementRegistrationService;
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

    public void main(String[] args) {

        try{

            if(args.length < 3) {
                throw new RuntimeException("引数が足りません");
            }

            kintaiManagementRegistrationRequest.setArgs(args);

            kintaiManagementRegistrationService.kintaiManagementRegistration(
                    kintaiManagementRegistrationRequest.makeKintaiManagementRegistrationInputArgs()
            );

        } catch (Exception e) {
            e.printStackTrace();
        }

        return;
    }
}
