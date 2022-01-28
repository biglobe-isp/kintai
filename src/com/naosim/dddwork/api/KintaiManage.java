package src.com.naosim.dddwork.api;

import src.com.naosim.dddwork.service.RegisterService;
import src.com.naosim.dddwork.service.TotalService;
import src.com.naosim.dddwork.domain.ResultTotal;

public class KintaiManage {

    RegisterService regSvc;
    TotalService ttlSvc;

    public KintaiManage() {
   }

    public void Input(String[] params) {

        String date = params[0];
        int startHour = Integer.valueOf(params[1].substring(0, 2));
        int startMinute = Integer.valueOf(params[1].substring(2, 4));
        int endHour = Integer.valueOf(params[2].substring(0, 2));
        int endMinute = Integer.valueOf(params[2].substring(2, 4));

        RegisterService regSvc = new RegisterService();
        regSvc.register(date, startHour, startMinute, endHour, endMinute);
    }

    public void Total(String yearMonth) {

        TotalService ttlSvc = new TotalService();
        ResultTotal result = ttlSvc.total(yearMonth);

        System.out.println("勤務時間: " + result.getTotalWork() / 60 + "時間" + result.getTotalWork() % 60 + "分");
        System.out.println("残業時間: " + result.getTotalOverWork() / 60 + "時間" + result.getTotalOverWork() % 60 + "分");

    }

}