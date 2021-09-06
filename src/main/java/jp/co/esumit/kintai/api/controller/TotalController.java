package jp.co.esumit.kintai.api.controller;

import jp.co.esumit.kintai.domain.kintai_record.KintaiRecord;
import jp.co.esumit.kintai.domain.summary.MonthlySummary;
import jp.co.esumit.kintai.domain.summary.target_year_month.TargetYearMonth;
import jp.co.esumit.kintai.domain.summary.total_actual_working_minutes.TotalActualWorkingMinutes;
import jp.co.esumit.kintai.domain.summary.total_overtime_minutes.TotalOvertimeMinutes;
import jp.co.esumit.kintai.service.MonthlySummaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class TotalController<T> {
    private final MonthlySummaryService monthlySummaryService;

    public void execute(String args[]) {
        TargetYearMonth targetYM = new TargetYearMonth(args[1]);

        List<KintaiRecord> list = monthlySummaryService.getRecordList(targetYM);
        MonthlySummary output = MonthlySummary.create(list);

        consoleOut(output);
    }

    private void consoleOut(MonthlySummary output) {
        System.out.println(outputString(output));
    }

    private String outputString(MonthlySummary output){

        return
                "実務時間：" + output.getTotalActualWorkingMinsValue() /60 + "時間"
                + output.getTotalActualWorkingMinsValue() % 60 + "分\n"
                + "残業時間：" + output.getTotalOvertimeValue() /60 + "時間"
                + output.getTotalOvertimeValue() % 60 + "分";
    }
}
