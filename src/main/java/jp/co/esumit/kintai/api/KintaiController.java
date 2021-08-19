package jp.co.esumit.kintai.api;

import jp.co.esumit.kintai.datasource.csv.CsvRepository;
import jp.co.esumit.kintai.domain.kintai_info.end_time.EndTime;
import jp.co.esumit.kintai.domain.kintai_info.start_time.StartTime;
import jp.co.esumit.kintai.domain.kintai_info.target_day.TargetDay;
import jp.co.esumit.kintai.domain.repository.KintaiRepository;
import jp.co.esumit.kintai.domain.summary.MonthlySummary;
import jp.co.esumit.kintai.domain.summary.target_year_month.TargetYearMonth;
import jp.co.esumit.kintai.service.KintaiInfoService;
import jp.co.esumit.kintai.service.MonthlySummaryService;
import org.springframework.stereotype.Controller;

@Controller
public class KintaiController {
    KintaiRepository repository = new CsvRepository();
    MonthlySummary output;

    public void executeInput(String args[]) {

        KintaiInfoService kintaiInfoService = new KintaiInfoService();

        //NOTE：仕様変更後は以下3行不要
        TargetDay targetday = new TargetDay(args[1]);
        StartTime startTime = new StartTime(args[2]);
        EndTime endTime = new EndTime(args[3]);

//  NOTE:仕様変更時の対応のため、現状は無視
//        Adaptor adp = new Adaptor();
//        InputPattern inputPattern = new InputPattern();
//        TargetDay targetday = adp.toTargetDay(args);
//        StartTime startTime;
//        EndTime endTime;
//
//        if (inputPattern.isVacation(args)) {
//
//            startTime = new StartTime("0900");
//            endTime = new EndTime("1800");
//        } else if (inputPattern.isAmOff(args)) {
//
//            startTime = new StartTime("0900");
//            endTime = adp.toEndTime(args);
//        } else if (inputPattern.isPmOff(args)) {
//
//            startTime = adp.toStartTime(args);
//            endTime = new EndTime("1800");
//        } else {
//
//            startTime = adp.toStartTime(args);
//            endTime = adp.toEndTime(args);
//        }

        kintaiInfoService.persist(targetday, startTime, endTime, repository);
    }

    public void executeTotal(String args[]) {

        MonthlySummaryService monthlySummaryService = new MonthlySummaryService();
        TargetYearMonth targetYM = new TargetYearMonth(args[1]);

        output = monthlySummaryService.getMonthlySummary(targetYM, repository);
    }

    public void consoleOut() {

        System.out.println("勤務時間: " + output.getTotalOfficeMinsValue() / 60 + "時間"
                                   + output.getTotalOfficeMinsValue() % 60 + "分");
        System.out.println("残業時間: " + output.getTotalOvertimeValue() / 60 + "時間"
                                   + output.getTotalOvertimeValue() % 60 + "分");
    }
}
