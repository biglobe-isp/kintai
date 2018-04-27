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

        try {
            // 入力パラメータのバリデーション処理未実装
            kintaiManagementRegistrationRequest.setArgs(args);
            kintaiManagementRegistrationRequest.checkRequestArgs();

            kintaiManagementRegistrationService.kintaiManagementRegistration(
                    kintaiManagementRegistrationRequest.makeKintaiManagementRegistrationServiceInput()
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
