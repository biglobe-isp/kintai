package com.naosim.dddwork.kintai_management.api.regist;

import com.naosim.dddwork.kintai_management.service.regist.KintaiManagementRegistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * 勤怠管理登録API
 */
@Controller
public class KintaiManagementRegistApi {

    @Autowired
    private KintaiManagementRegistRequest kintaiManagementRegistRequest;

    @Autowired
    private KintaiManagementRegistService kintaiManagementRegistService;

    public void main(String[] args) {

        try {
            // 入力パラメータのバリデーション処理未実装
            kintaiManagementRegistRequest.setArgs(args);
            kintaiManagementRegistRequest.checkRequestArgs();

            kintaiManagementRegistService.kintaiManagementRegist(
                    kintaiManagementRegistRequest.makeKintaiManagementRegistServiceInput()
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
