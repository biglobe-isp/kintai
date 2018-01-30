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

        // 対象年月の日毎の勤怠オブジェクトをリストで取得
        List<KintaiOfOneDay> targetMonthList = this.kintaiOfDays.getTargetMonthList(this.kintaiTotalPrintTargetYearMonth);

        // 合計勤務時間
        this.totalWorkMinutes =
                new TotalWorkMinutes(targetMonthList.stream()
                        .mapToInt(kintaiOfOneDay -> kintaiOfOneDay.getWorkMinutes().getInt()).sum()
                );

        // 合計残業時間
        this.totalOverWorkMinutes =
                new TotalOverWorkMinutes(targetMonthList.stream()
                        .mapToInt(kintaiOfOneDay -> kintaiOfOneDay.getOverWorkMinutes().getInt()).sum()
                );
    }
}
