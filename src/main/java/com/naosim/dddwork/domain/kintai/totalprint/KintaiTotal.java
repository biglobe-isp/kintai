package com.naosim.dddwork.domain.kintai.totalprint;


import com.naosim.dddwork.domain.kintai.KintaiOfOneDay;
import com.naosim.dddwork.domain.kintai.totalprint.time.TotalOverWorkMinutes;
import com.naosim.dddwork.domain.kintai.totalprint.time.TotalWorkMinutes;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@EqualsAndHashCode
@ToString
public class KintaiTotal {

    @Getter
    private final KintaiTotalPrintTargetYearMonth kintaiTotalPrintTargetYearMonth;

    @Getter
    private final KintaiOfDays kintaiOfDays;

    @Getter
    private final TotalWorkMinutes totalWorkMinutes;

    @Getter
    private final TotalOverWorkMinutes totalOverWorkMinutes;

    public KintaiTotal(KintaiTotalPrintTargetYearMonth kintaiTotalPrintTargetYearMonth, KintaiOfDays kintaiOfDays) {
        this.kintaiTotalPrintTargetYearMonth = kintaiTotalPrintTargetYearMonth;
        this.kintaiOfDays = kintaiOfDays;

        List<KintaiOfOneDay> targetMonthList = this.kintaiOfDays.getTargetMonthList(this.kintaiTotalPrintTargetYearMonth);
        this.totalWorkMinutes = KintaiTotal.getTotalWorkMinutes(targetMonthList);
        this.totalOverWorkMinutes = KintaiTotal.getTotalOverWorkMinutes(targetMonthList);
    }

    private static TotalWorkMinutes getTotalWorkMinutes(List<KintaiOfOneDay> targetList) {
        return new TotalWorkMinutes(targetList.stream()
                .mapToInt(kintaiOfOneDay -> kintaiOfOneDay.getWorkMinutes().getInt()).sum()
        );
    }

    private static TotalOverWorkMinutes getTotalOverWorkMinutes(List<KintaiOfOneDay> targetList) {
        return new TotalOverWorkMinutes(targetList.stream()
                .mapToInt(kintaiOfOneDay -> kintaiOfOneDay.getOverWorkMinutes().getInt()).sum()
        );
    }
}
