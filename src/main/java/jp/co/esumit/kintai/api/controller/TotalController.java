package jp.co.esumit.kintai.api.controller;

import jp.co.esumit.kintai.datasource.csv.CsvRepository;
import jp.co.esumit.kintai.domain.repository.KintaiRepository;
import jp.co.esumit.kintai.domain.summary.MonthlySummary;
import jp.co.esumit.kintai.domain.summary.target_year_month.TargetYearMonth;
import jp.co.esumit.kintai.service.MonthlySummaryService;

public class TotalController {
    KintaiRepository repository = new CsvRepository();

    public void execute(String args[]) {

        MonthlySummaryService monthlySummaryService = new MonthlySummaryService();
        TargetYearMonth targetYM = new TargetYearMonth(args[1]);

        MonthlySummary output = monthlySummaryService.getMonthlySummary(targetYM, repository);

        consoleOut(output);
    }

    private void consoleOut(MonthlySummary output) {

        System.out.println("実務時間: " + output.getTotalOfficeMinsValue() / 60 + "時間"
                                   + output.getTotalOfficeMinsValue() % 60 + "分");
        System.out.println("残業時間: " + output.getTotalOvertimeValue() / 60 + "時間"
                                   + output.getTotalOvertimeValue() % 60 + "分");
    }
}
