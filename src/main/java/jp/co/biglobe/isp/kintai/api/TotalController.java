package jp.co.biglobe.isp.kintai.api;

import jp.co.biglobe.isp.kintai.domain.monthly_accumulated_hour.MonthlyAccumulatedWorkMinutes;
import jp.co.biglobe.isp.kintai.service.monthly_accumulated_hour.MonthlyAccumulatedHourReferenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class TotalController {
    private final MonthlyAccumulatedHourReferenceService monthlyAccumulatedHourReferenceService;

    public MonthlyAccumulatedWorkMinutes run(String[] args) {
        String yearMonth = args[1];

        YearMonthValidator.isValid(args[1]);

        var monthlyAccumulatedWorkMinutes = monthlyAccumulatedHourReferenceService.refer(yearMonth);

        return monthlyAccumulatedWorkMinutes;
    }
}
