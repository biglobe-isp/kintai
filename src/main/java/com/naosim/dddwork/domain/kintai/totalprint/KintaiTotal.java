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
    private TotalWorkMinutes totalWorkMinutes;

    @Getter
    private TotalOverWorkMinutes totalOverWorkMinutes;

    public KintaiTotal(KintaiTotalPrintTargetYearMonth kintaiTotalPrintTargetYearMonth, KintaiOfDays kintaiOfDays) {
        this.kintaiTotalPrintTargetYearMonth = kintaiTotalPrintTargetYearMonth;
        this.kintaiOfDays = kintaiOfDays;
        this.setTotalData();
    }

    private void setTotalData() {

        List<KintaiOfOneDay> targetMonthList = this.kintaiOfDays.getTargetMonthList(this.kintaiTotalPrintTargetYearMonth);

        this.setTotalWorkMinutes(targetMonthList);
        this.setTotalOverWorkMinutes(targetMonthList);
    }

    private void setTotalWorkMinutes(List<KintaiOfOneDay> targetList) {
        this.totalWorkMinutes =
                new TotalWorkMinutes(targetList.stream()
                        .mapToInt(kintaiOfOneDay -> kintaiOfOneDay.getWorkMinutes().getInt()).sum()
                );
    }

    private void setTotalOverWorkMinutes(List<KintaiOfOneDay> targetList) {
        this.totalOverWorkMinutes =
                new TotalOverWorkMinutes(targetList.stream()
                        .mapToInt(kintaiOfOneDay -> kintaiOfOneDay.getOverWorkMinutes().getInt()).sum()
                );
    }
}
