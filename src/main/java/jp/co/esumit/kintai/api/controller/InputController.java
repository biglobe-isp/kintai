package jp.co.esumit.kintai.api.controller;

import jp.co.esumit.kintai.domain.kintai_record.target_day.TargetDay;
import jp.co.esumit.kintai.domain.kintai_record.time_card.EndTime;
import jp.co.esumit.kintai.domain.kintai_record.time_card.StartTime;
import jp.co.esumit.kintai.service.KintaiRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class InputController {
    private final KintaiRecordService kintaiRecordService;

    public void execute(String args[]) {

        //NOTE：仕様変更後は以下3行不要
        TargetDay targetday = new TargetDay(args[1]);
        StartTime startTime = new StartTime(args[2]);
        EndTime endTime = new EndTime(args[3]);

//  NOTE:仕様変更時の対応のため、現状は無視
//        Adaptor adp = new Adaptor();
//        InputPattern inputPattern = new InputPattern();
//        TargetDay targetday = adp.toTargetDay(args);
//        StartTimeOld startTimeOld;
//        EndTime endTime;
//
//        if (inputPattern.isVacation(args)) {
//
//            startTimeOld = new StartTimeOld("0900");
//            endTime = new EndTime("1800");
//        } else if (inputPattern.isAmOff(args)) {
//
//            startTimeOld = new StartTimeOld("0900");
//            endTime = adp.toEndTime(args);
//        } else if (inputPattern.isPmOff(args)) {
//
//            startTimeOld = adp.toStartTime(args);
//            endTime = new EndTime("1800");
//        } else {
//
//            startTimeOld = adp.toStartTime(args);
//            endTime = adp.toEndTime(args);
//        }

        kintaiRecordService.persist(targetday, startTime, endTime);
    }
}
