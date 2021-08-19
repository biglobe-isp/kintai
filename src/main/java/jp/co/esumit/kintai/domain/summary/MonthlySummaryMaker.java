package jp.co.esumit.kintai.domain.summary;

import jp.co.esumit.kintai.domain.kintai_info.KintaiInfo;
import jp.co.esumit.kintai.domain.summary.total_office_minutes.TotalOfficeMinutes;
import jp.co.esumit.kintai.domain.summary.total_office_minutes.TotalOfficeMinutesCalculator;
import jp.co.esumit.kintai.domain.summary.total_overtime_minutes.TotalOvertimeMinutes;
import jp.co.esumit.kintai.domain.summary.total_overtime_minutes.TotalOvertimeMinutesCalculator;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class MonthlySummaryMaker {
    private final TotalOfficeMinutesCalculator totalOfficeMinutesCalculator;
    private final TotalOvertimeMinutesCalculator totalOvertimeMinutesCalculator;

    public MonthlySummary create(List<KintaiInfo> kintaiInfoList) {

        TotalOfficeMinutes totalOfficeMinutes = totalOfficeMinutesCalculator.calc(kintaiInfoList);
        TotalOvertimeMinutes totalOvertimeMinutes = totalOvertimeMinutesCalculator.calc(kintaiInfoList);

        return new MonthlySummary(totalOfficeMinutes, totalOvertimeMinutes);
    }
}
