package com.naosim.dddwork.kintai_management.api.total;

import com.naosim.dddwork.kintai_management.service.total.KintaiManagementTotalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * 勤怠管理集計API
 */
@Controller
public class KintaiManagementTotalApi {

    @Autowired
    private KintaiManagementTotalRequest kintaiManagementTotalRequest;

    @Autowired
    private KintaiManagementTotalService kintaiManagementTotalService;

    public void main(String[] args) {

        try {
            // 入力パラメータのバリデーション処理未実装
            kintaiManagementTotalRequest.setArgs(args);
            kintaiManagementTotalRequest.checkRequestArgs();

            kintaiManagementTotalService.kintaiManagementTotal(
                    kintaiManagementTotalRequest.makeKintaiManagementTotalServiceInput()
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
