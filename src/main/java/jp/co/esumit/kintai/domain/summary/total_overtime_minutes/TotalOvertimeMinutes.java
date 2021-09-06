package jp.co.esumit.kintai.domain.summary.total_overtime_minutes;

import jp.co.esumit.kintai.domain.kintai_record.KintaiRecord;
import jp.co.esumit.kintai.domain.kintai_record.target_day.TargetDay;
import jp.co.esumit.kintai.domain.kintai_record.working_minutes.overtime_minutes.OvertimeMinutes;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.Value;

import java.util.List;
import java.util.Map;

@Value
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class TotalOvertimeMinutes {
    int value;

    public static TotalOvertimeMinutes create(List<KintaiRecord> kintaiRecordList) {

        return new TotalOvertimeMinutes(kintaiRecordList.stream()
                                                .mapToInt(KintaiRecord::getOvertimeValue)
                                                .reduce(0, Integer::sum));
    }
}
