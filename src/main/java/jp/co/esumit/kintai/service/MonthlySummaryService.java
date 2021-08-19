package jp.co.esumit.kintai.service;

import jp.co.esumit.kintai.domain.kintai_info.KintaiInfo;
import jp.co.esumit.kintai.domain.repository.KintaiRepository;
import jp.co.esumit.kintai.domain.summary.MonthlySummary;
import jp.co.esumit.kintai.domain.summary.MonthlySummaryMaker;
import jp.co.esumit.kintai.domain.summary.target_year_month.TargetYearMonth;
import jp.co.esumit.kintai.domain.summary.total_office_minutes.TotalOfficeMinutesCalculator;
import jp.co.esumit.kintai.domain.summary.total_overtime_minutes.TotalOvertimeMinutesCalculator;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MonthlySummaryService {
    public MonthlySummary getMonthlySummary(TargetYearMonth targetYM, KintaiRepository repository) {

        List<KintaiInfo> targetList = repository.read(targetYM);

        MonthlySummary monthlySummary = new MonthlySummaryMaker(
                new TotalOfficeMinutesCalculator(),
                new TotalOvertimeMinutesCalculator()
        ).create(targetList);

        return monthlySummary;
    }
}
