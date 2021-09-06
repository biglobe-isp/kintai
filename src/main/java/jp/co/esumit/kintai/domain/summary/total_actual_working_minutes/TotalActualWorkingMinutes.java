package jp.co.esumit.kintai.domain.summary.total_actual_working_minutes;

import jp.co.esumit.kintai.domain.kintai_record.KintaiRecord;
import jp.co.esumit.kintai.domain.kintai_record.target_day.TargetDay;
import jp.co.esumit.kintai.domain.kintai_record.working_minutes.operation_and_working_minutes.ActualWorkingMinutes;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.Value;

import java.util.List;
import java.util.Map;

@Value
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class TotalActualWorkingMinutes {
    int value;

    public static TotalActualWorkingMinutes create(List<KintaiRecord> kintaiRecordList) {

        return new TotalActualWorkingMinutes(kintaiRecordList.stream()
                                                     .mapToInt(KintaiRecord::getActualWorkingMinutesValue)
                                                     .reduce(0, Integer::sum));
    }
}
