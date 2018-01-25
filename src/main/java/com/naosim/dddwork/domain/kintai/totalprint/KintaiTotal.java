package com.naosim.dddwork.domain.kintai.totalprint;


import com.naosim.dddwork.domain.kintai.KintaiOfOneDay;
import com.naosim.dddwork.domain.kintai.time.work.OverWorkMinutes;
import com.naosim.dddwork.domain.kintai.time.work.WorkDate;
import com.naosim.dddwork.domain.kintai.time.work.WorkMinutes;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

@EqualsAndHashCode
@ToString
public class KintaiTotal {

    @Getter
    private final KintaiTotalPrintTargetYearMonth kintaiTotalPrintTargetYearMonth;

    @Getter
    private final KintaiOfOneDays kintaiOfOneDays;

    @Getter
    private int totalWorkMinutes;

    @Getter
    private int totalOverWorkMinutes;

    public KintaiTotal(KintaiTotalPrintTargetYearMonth kintaiTotalPrintTargetYearMonth, KintaiOfOneDays kintaiOfOneDays) {
        this.kintaiTotalPrintTargetYearMonth = kintaiTotalPrintTargetYearMonth;
        this.kintaiOfOneDays = kintaiOfOneDays;
        this.setTotalData();
    }

    private void setTotalData() {
        this.totalWorkMinutes = 0;
        this.totalOverWorkMinutes = 0;

        Map<WorkDate, WorkMinutes> totalWorkMinutesMap = new HashMap<>();
        Map<WorkDate, OverWorkMinutes> totalOverWorkMinutesMap = new HashMap<>();

        Iterator<KintaiOfOneDay> iterator = this.kintaiOfOneDays.getIterator();
        while (iterator.hasNext()) {
            KintaiOfOneDay kintaiOfOneDay = iterator.next();

            if (!kintaiOfOneDay.getWorkDate().isTargetYearMonth(this.kintaiTotalPrintTargetYearMonth.getWorkYearMonth())) {
                continue;
            }

            totalWorkMinutesMap.put(kintaiOfOneDay.getWorkDate(), kintaiOfOneDay.getWorkMinutes());
            totalOverWorkMinutesMap.put(kintaiOfOneDay.getWorkDate(), kintaiOfOneDay.getOverWorkMinutes());
        }

        Set<WorkDate> keySet = totalWorkMinutesMap.keySet();
        for (WorkDate key : keySet) {
            this.totalWorkMinutes += totalWorkMinutesMap.get(key).getInt();
            this.totalOverWorkMinutes += totalOverWorkMinutesMap.get(key).getInt();
        }
    }
}
