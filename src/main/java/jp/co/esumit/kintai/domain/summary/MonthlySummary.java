package jp.co.esumit.kintai.domain.summary;

import jp.co.esumit.kintai.domain.kintai_record.KintaiRecord;
import jp.co.esumit.kintai.domain.kintai_record.target_day.TargetDay;
import jp.co.esumit.kintai.domain.kintai_record.working_minutes.operation_and_working_minutes.ActualWorkingMinutes;
import jp.co.esumit.kintai.domain.kintai_record.working_minutes.overtime_minutes.OvertimeMinutes;
import jp.co.esumit.kintai.domain.summary.total_actual_working_minutes.TotalActualWorkingMinutes;
import jp.co.esumit.kintai.domain.summary.total_overtime_minutes.TotalOvertimeMinutes;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.Value;

import java.util.List;
import java.util.Map;

@Value
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class MonthlySummary {
    TotalActualWorkingMinutes totalActualWorkingMinutes;
    TotalOvertimeMinutes totalOvertimeMinutes;

    public static MonthlySummary create(List<KintaiRecord> kintaiRecordList) {

        TotalActualWorkingMinutes totalActualWorkingMinutes = TotalActualWorkingMinutes.create(kintaiRecordList);
        TotalOvertimeMinutes totalOvertimeMinutes = TotalOvertimeMinutes.create(kintaiRecordList);

        return new MonthlySummary(totalActualWorkingMinutes, totalOvertimeMinutes);
    }

    public int getTotalActualWorkingMinsValue() {
        return this.totalActualWorkingMinutes.getValue();
    }

    public int getTotalOvertimeValue() {
        return this.totalOvertimeMinutes.getValue();
    }
}
