package com.naosim.dddwork.kintai_management.api.total;

import com.naosim.dddwork.kintai_management.api.form.TotalYearMonthForm;
import com.naosim.dddwork.kintai_management.service.total.KintaiManagementTotalService;
import lombok.Getter;
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

//    @Getter
//    private final static String URI = "/kintai-management-total";

    public void main(String[] args) {

        try{

            if(args.length < 1) {
                throw new RuntimeException("引数が足りません");
            }

            kintaiManagementTotalRequest.setTotalYearMonthForm(new TotalYearMonthForm(args[0]));

            kintaiManagementTotalService.kintaiManagementTotal(
                    kintaiManagementTotalRequest.makeKintaiManagementTotalInput()
            );

        } catch (Exception e) {
            e.printStackTrace();
        }

        return;
    }
}
