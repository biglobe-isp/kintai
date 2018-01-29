package com.naosim.dddwork.domain.kintai.totalprint;


import com.naosim.dddwork.domain.kintai.KintaiOfOneDay;
import com.naosim.dddwork.domain.kintai.time.work.OverWorkMinutes;
import com.naosim.dddwork.domain.kintai.time.work.WorkDate;
import com.naosim.dddwork.domain.kintai.time.work.WorkMinutes;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.*;
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

        Map<WorkDate, WorkMinutes> totalWorkMinutesMap = new HashMap<>();
        Map<WorkDate, OverWorkMinutes> totalOverWorkMinutesMap = new HashMap<>();

        Iterator<KintaiOfOneDay> iterator = this.kintaiOfDays.getIterator();
        while (iterator.hasNext()) {
            KintaiOfOneDay kintaiOfOneDay = iterator.next();

            if (!kintaiOfOneDay.getWorkDate().isTargetYearMonth(this.kintaiTotalPrintTargetYearMonth.getWorkYearMonth())) {
                continue;
            }

            totalWorkMinutesMap.put(kintaiOfOneDay.getWorkDate(), kintaiOfOneDay.getWorkMinutes());
            totalOverWorkMinutesMap.put(kintaiOfOneDay.getWorkDate(), kintaiOfOneDay.getOverWorkMinutes());
        }

        // 日毎の勤怠オブジェクトをリストで取得
        List<KintaiOfOneDay> list = this.kintaiOfDays.getKintaiOfOneDayList();

        // 実行日時でソートするために使用
        Comparator<KintaiOfOneDay> comparator = Comparator.comparing(kintaiOfOneDay -> kintaiOfOneDay.getNow().getValue());

        // 重複チェックのために使用するSet
        Set<WorkDate> workDateSet = new HashSet<>();

        // 対象データを抽出して新たにリスト化
        List<KintaiOfOneDay> filtered = list.stream()
                .sorted(comparator.reversed())
                // 対象年月データのみ抽出
                .filter(e -> e.getWorkDate().isTargetYearMonth(this.kintaiTotalPrintTargetYearMonth.getWorkYearMonth()))
                // 重複データ（同一勤務日）は後勝ちとし、最新データ以外は削除
                .filter(e -> workDateSet.add(e.getWorkDate()))
                .collect(Collectors.toList());

        // 合計勤務時間
        this.totalWorkMinutes = filtered.stream()
                .map(kintaiOfOneDay -> kintaiOfOneDay.getWorkMinutes().getInt())
                .reduce((value1, value2) -> value1 + value2).orElse(0);

        // 合計残業時間
        this.totalOverWorkMinutes = filtered.stream()
                .map(kintaiOfOneDay -> kintaiOfOneDay.getOverWorkMinutes().getInt())
                .reduce((value1, value2) -> value1 + value2).orElse(0);

//        Set<WorkDate> workDateSet2 = new HashSet<>();
//        this.totalWorkMinutes = list.stream()
//                .sorted(comparator.reversed())
//                .filter(e -> workDateSet2.add(e.getWorkDate()))
//                .map(kintaiOfOneDay -> kintaiOfOneDay.getWorkMinutes().getInt())
//                .reduce((value1, value2) -> value1 + value2).orElse(0);
//
//        Set<WorkDate> workDateSet3 = new HashSet<>();
//        this.totalOverWorkMinutes = list.stream()
//                .sorted(comparator.reversed())
//                .filter(e -> workDateSet3.add(e.getWorkDate()))
//                .map(kintaiOfOneDay -> kintaiOfOneDay.getOverWorkMinutes().getInt())
//                .reduce((value1, value2) -> value1 + value2).orElse(0);

//        Set<WorkDate> keySet = totalWorkMinutesMap.keySet();
//        for (WorkDate key : keySet) {
//            this.totalWorkMinutes += totalWorkMinutesMap.get(key).getInt();
//            this.totalOverWorkMinutes += totalOverWorkMinutesMap.get(key).getInt();
//        }
    }
}
