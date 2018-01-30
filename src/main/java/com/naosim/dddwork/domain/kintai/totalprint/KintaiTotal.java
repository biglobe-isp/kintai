package com.naosim.dddwork.domain.kintai.totalprint;


import com.naosim.dddwork.domain.kintai.KintaiOfOneDay;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.List;
import java.util.stream.Collectors;

@EqualsAndHashCode
@ToString
public class KintaiTotal {

    @Getter
    private final KintaiTotalPrintTargetYearMonth kintaiTotalPrintTargetYearMonth;

    @Getter
    private final KintaiOfDays kintaiOfDays;

    @Getter
    private int totalWorkMinutes;

    @Getter
    private int totalOverWorkMinutes;

    public KintaiTotal(KintaiTotalPrintTargetYearMonth kintaiTotalPrintTargetYearMonth, KintaiOfDays kintaiOfDays) {
        this.kintaiTotalPrintTargetYearMonth = kintaiTotalPrintTargetYearMonth;
        this.kintaiOfDays = kintaiOfDays;
        this.setTotalData();
    }

    private void setTotalData() {
        this.totalWorkMinutes = 0;
        this.totalOverWorkMinutes = 0;

        // 日毎の勤怠オブジェクトをリストで取得
        List<KintaiOfOneDay> list = this.kintaiOfDays.getUnmodifiableList();

        // 対象データを抽出して新たにリスト化
        List<KintaiOfOneDay> filtered = list.stream()
                // 対象年月データのみ抽出
                .filter(e -> e.getWorkDate().isTargetYearMonth(this.kintaiTotalPrintTargetYearMonth.getWorkYearMonth()))
                .collect(Collectors.toList());

        // 合計勤務時間
        this.totalWorkMinutes = filtered.stream()
                .mapToInt(kintaiOfOneDay -> kintaiOfOneDay.getWorkMinutes().getInt()).sum();

        // 合計残業時間
        this.totalOverWorkMinutes = filtered.stream()
                .mapToInt(kintaiOfOneDay -> kintaiOfOneDay.getOverWorkMinutes().getInt()).sum();
    }
}
