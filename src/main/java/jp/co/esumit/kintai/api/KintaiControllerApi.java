package jp.co.esumit.kintai.api;

import jp.co.esumit.kintai.domain.field.EndTime;
import jp.co.esumit.kintai.domain.field.StartTime;
import jp.co.esumit.kintai.domain.field.TargetDay;
import jp.co.esumit.kintai.domain.field.TargetYearMonth;
import jp.co.esumit.kintai.service.KintaiService;

public class KintaiControllerApi {

    KintaiService kintaiService = new KintaiService();

    public void executeInput(String args[]){

        TargetDay targetday = new TargetDay(args[1]);
        StartTime startTime = new StartTime(args[2]);
        EndTime endTime = new EndTime(args[3]);

        kintaiService.inputService(targetday,startTime,endTime);

    }

    public void executeTotal(String args[]){

        TargetYearMonth targetYM = new TargetYearMonth(args[1]);

        kintaiService.totalService(targetYM);
    }
}
