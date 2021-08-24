package jp.co.esumit.kintai.domain.summary.total_overtime_minutes;

import jp.co.esumit.kintai.domain.kintai_info.KintaiInfo;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.Value;

import java.util.List;

@Value
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class TotalOvertimeMinutes {
    int value;

    public static TotalOvertimeMinutes create(List<KintaiInfo> targetList) {

        return new TotalOvertimeMinutes(
                targetList.stream()
                        .mapToInt(KintaiInfo::getOvertimeValue)
                        .reduce(0, Integer::sum));
    }
}
