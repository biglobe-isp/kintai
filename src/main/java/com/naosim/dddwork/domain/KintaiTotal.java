package com.naosim.dddwork.domain;

import com.naosim.dddwork.domain.time.Minute;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

@EqualsAndHashCode(callSuper = false)
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

        Map<String, Minute> totalWorkMinutesMap = new HashMap<>();
        Map<String, Minute> totalOverWorkMinutesMap = new HashMap<>();

        Iterator<KintaiOfOneDayLine> iterator = this.kintaiOfOneDayLines.getIterator();
        while (iterator.hasNext()) {
            KintaiOfOneDayLine kintaiOfOneDayLine = iterator.next();
            KintaiOfOneDay kintaiOfOneDay = kintaiOfOneDayLine.getKintaiOfOneDay();

            if (!kintaiOfOneDay.getWorkDate().isTargetYearMonth(this.kintaiTotalPrintInput.getWorkYearMonth())) {
                continue;
            }
            totalWorkMinutesMap.put(kintaiOfOneDay.getWorkDate().getValue(), kintaiOfOneDay.getWorkMinutes());
            totalOverWorkMinutesMap.put(kintaiOfOneDay.getWorkDate().getValue(), kintaiOfOneDay.getOverWorkMinutes());
        }

        Set<String> keySet = totalWorkMinutesMap.keySet();
        for (String key : keySet) {
            this.totalWorkMinutes += totalWorkMinutesMap.get(key).getValue();
            this.totalOverWorkMinutes += totalOverWorkMinutesMap.get(key).getValue();
        }
    }
}
