package src.com.naosim.dddwork.api;

import src.com.naosim.dddwork.service.RegistService;
import src.com.naosim.dddwork.service.TotalService;
import java.util.Arrays;

public class KintaiManage {

    RegistService regSvc;
    TotalService ttlSvc;

    public KintaiManage() {
   }

    public void Input(String[] params) {

        // サービス起動
        RegistService regSvc = new RegistService();
        regSvc.regist(params[0], params[1], params[3]);
    }

    public void Total(String yearMonth) {

        // サービス起動
        TotalService ttlSvc = new TotalService();
        ttlSvc.total(yearMonth);
    }

}