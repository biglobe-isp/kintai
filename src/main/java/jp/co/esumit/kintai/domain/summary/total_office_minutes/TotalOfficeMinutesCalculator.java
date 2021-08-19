package jp.co.esumit.kintai.domain.summary.total_office_minutes;

import jp.co.esumit.kintai.domain.kintai_info.KintaiInfo;

import java.util.List;

public class TotalOfficeMinutesCalculator {
    public TotalOfficeMinutes calc(List<KintaiInfo> targetList) {

        return new TotalOfficeMinutes(
                targetList.stream()
                        .mapToInt(KintaiInfo::getOfficeMinutesValue)
                        .reduce(0, Integer::sum));
    }
}
