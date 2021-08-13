package jp.co.esumit.kintai.api;

import jp.co.esumit.kintai.datasource.AppConst;
import jp.co.esumit.kintai.domain.field.EndTime;
import jp.co.esumit.kintai.domain.field.StartTime;
import jp.co.esumit.kintai.domain.field.TargetDay;
import jp.co.esumit.kintai.domain.field.TargetYearMonth;
import jp.co.esumit.kintai.service.KintaiService;

import java.util.Arrays;
import java.util.stream.Stream;

public class KintaiControllerApi {

    KintaiService kintaiService = new KintaiService();

    public void executeInput(String args[]){

        Adaptor adp = new Adaptor();

        TargetDay targetday = adp.toTargetDay(args);
        StartTime startTime;
        EndTime endTime;

        //　休暇
        if(Arrays.stream(args).anyMatch(x -> x.equals("v"))){

            startTime = new StartTime("0900");
            endTime = new EndTime("1800");

        //　AM休
        }else if (Arrays.stream(args).anyMatch(x -> x.equals("am"))
                && Arrays.stream(args).anyMatch(x -> x.startsWith(AppConst.Prefix.END))){

            startTime = new StartTime("0900");
            endTime = adp.toEndTime(args);

        //　PM休
        }else if (Arrays.stream(args).anyMatch(x -> x.equals("pm"))
                && Arrays.stream(args).anyMatch(x -> x.startsWith(AppConst.Prefix.START))) {

            startTime = adp.toStartTime(args);
            endTime = new EndTime("1800");

        //　通常
        }else{

            startTime = adp.toStartTime(args);
            endTime = adp.toEndTime(args);

        }

        kintaiService.inputService(targetday,startTime,endTime);

    }

    public void executeTotal(String args[]){

        TargetYearMonth targetYM = new TargetYearMonth(args[1]);

        kintaiService.totalService(targetYM);
    }
}
