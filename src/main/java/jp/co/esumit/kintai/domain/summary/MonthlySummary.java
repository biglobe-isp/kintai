package jp.co.esumit.kintai.domain.summary;

import jp.co.esumit.kintai.domain.kintai_info.KintaiInfo;
import jp.co.esumit.kintai.domain.summary.total_actual_working_minutes.TotalActualWorkingMinutes;
import jp.co.esumit.kintai.domain.summary.total_overtime_minutes.TotalOvertimeMinutes;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.Value;

import java.util.List;

@Value
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class MonthlySummary {
    TotalActualWorkingMinutes totalActualWorkingMinutes;
    TotalOvertimeMinutes totalOvertimeMinutes;

    public static MonthlySummary create(List<KintaiInfo> kintaiInfoList) {

        TotalActualWorkingMinutes totalActualWorkingMinutes = TotalActualWorkingMinutes.create(kintaiInfoList);
        TotalOvertimeMinutes totalOvertimeMinutes = TotalOvertimeMinutes.create(kintaiInfoList);

        return new MonthlySummary(totalActualWorkingMinutes, totalOvertimeMinutes);
    }

    public int getTotalOfficeMinsValue() {
        return this.totalActualWorkingMinutes.getValue();
    }

    public int getTotalOvertimeValue() {
        return this.totalOvertimeMinutes.getValue();
    }
}
