package jp.co.esumit.kintai.domain.summary.total_overtime_minutes;

import jp.co.esumit.kintai.domain.kintai_info.KintaiInfo;

import java.util.List;

public class TotalOvertimeMinutesCalculator {
    public TotalOvertimeMinutes calc(List<KintaiInfo> targetList) {

        return new TotalOvertimeMinutes(
                targetList.stream()
                        .mapToInt(KintaiInfo::getOvertimeValue)
                        .reduce(0, Integer::sum));
    }
}
