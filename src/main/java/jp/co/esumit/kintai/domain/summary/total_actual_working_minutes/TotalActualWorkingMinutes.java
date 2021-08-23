package jp.co.esumit.kintai.domain.summary.total_actual_working_minutes;

import jp.co.esumit.kintai.domain.kintai_info.KintaiInfo;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.Value;

import java.util.List;

@Value
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class TotalActualWorkingMinutes {
    private final int value;

    public static TotalActualWorkingMinutes create(List<KintaiInfo> targetList) {

        return new TotalActualWorkingMinutes(
                targetList.stream()
                        .mapToInt(KintaiInfo::getActualWorkingMinutesValue)
                        .reduce(0, Integer::sum));
    }
}
