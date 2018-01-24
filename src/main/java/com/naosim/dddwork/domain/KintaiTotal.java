package com.naosim.dddwork.domain;


import com.naosim.dddwork.domain.time.work.OverWorkMinutes;
import com.naosim.dddwork.domain.time.work.WorkDate;
import com.naosim.dddwork.domain.time.work.WorkMinutes;
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
    KintaiTotalPrintInput kintaiTotalPrintInput;

    @Getter
    private KintaiOfOneDayLines kintaiOfOneDayLines;

    @Getter
    private int totalWorkMinutes;

    @Getter
    private int totalOverWorkMinutes;

    public KintaiTotal(KintaiTotalPrintInput kintaiTotalPrintInput, KintaiOfOneDayLines kintaiOfOneDayLines) {
        this.kintaiTotalPrintInput = kintaiTotalPrintInput;
        this.kintaiOfOneDayLines = kintaiOfOneDayLines;
        this.setTotalData();
    }

    private void setTotalData() {
        this.totalWorkMinutes = 0;
        this.totalOverWorkMinutes = 0;

        Map<WorkDate, WorkMinutes> totalWorkMinutesMap = new HashMap<>();
        Map<WorkDate, OverWorkMinutes> totalOverWorkMinutesMap = new HashMap<>();

        Iterator<KintaiOfOneDayLine> iterator = this.kintaiOfOneDayLines.getIterator();
        while (iterator.hasNext()) {
            KintaiOfOneDayLine kintaiOfOneDayLine = iterator.next();
            KintaiOfOneDay kintaiOfOneDay = kintaiOfOneDayLine.getKintaiOfOneDay();

            if (!kintaiOfOneDay.getWorkDate().isTargetYearMonth(this.kintaiTotalPrintInput.getWorkYearMonth())) {
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
