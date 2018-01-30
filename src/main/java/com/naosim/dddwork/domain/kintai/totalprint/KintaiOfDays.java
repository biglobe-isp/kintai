package com.naosim.dddwork.domain.kintai.totalprint;

import com.naosim.dddwork.domain.kintai.KintaiOfOneDay;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class KintaiOfDays {

    private List<KintaiOfOneDay> kintaiOfOneDayList;

    public KintaiOfDays(List<KintaiOfOneDay> kintaiOfOneDayLineList) {
        this.kintaiOfOneDayList = kintaiOfOneDayLineList;
    }

    public KintaiOfDays add(KintaiOfOneDay kintaiOfOneDay) {
        List<KintaiOfOneDay> tmpKintaiOfOneDayList = new ArrayList<>(this.kintaiOfOneDayList);
        tmpKintaiOfOneDayList.add(kintaiOfOneDay);
        return new KintaiOfDays(tmpKintaiOfOneDayList);
    }

    public Iterator<KintaiOfOneDay> getIterator() {
        return this.kintaiOfOneDayList.iterator();
    }

    public List<KintaiOfOneDay> getUnmodifiableList() {
        return Collections.unmodifiableList(this.kintaiOfOneDayList);
    }

    public List<KintaiOfOneDay> getTargetMonthList(KintaiTotalPrintTargetYearMonth kintaiTotalPrintTargetYearMonth) {
        // 対象データを抽出して新たにリスト化
        return this.kintaiOfOneDayList.stream()
                // 対象年月データのみ抽出
                .filter(e -> e.getWorkDate().isTargetYearMonth(kintaiTotalPrintTargetYearMonth.getWorkYearMonth()))
                .collect(Collectors.toList());
    }
}
