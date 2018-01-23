package com.naosim.dddwork.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.*;

@EqualsAndHashCode(callSuper = false)
@ToString
public class KintaiTotal {

    @Getter
    KintaiTotalPrintInput kintaiTotalPrintInput;

    @Getter
    private KintaiLines kintaiLines;

    @Getter
    private int totalWorkMinutes;

    @Getter
    private int totalOverWorkMinutes;

    public KintaiTotal(KintaiTotalPrintInput kintaiTotalPrintInput, KintaiLines kintaiLines) {
        this.kintaiTotalPrintInput = kintaiTotalPrintInput;
        this.kintaiLines = kintaiLines;
        this.setTotalData();
    }

    private void setTotalData() {
        this.totalWorkMinutes = 0;
        this.totalOverWorkMinutes = 0;

        Map<String, Integer> totalWorkMinutesMap = new HashMap<>();
        Map<String, Integer> totalOverWorkMinutesMap = new HashMap<>();

        Iterator<String> iterator = this.kintaiLines.getIterator();
        while (iterator.hasNext()) {
            String line = iterator.next();
            String[] columns = line.split(",");

            OneDayKintai oneDayKintai = new OneDayKintai(columns[0], columns[1], columns[2], columns[3], columns[4], columns[5]);

            if(!oneDayKintai.getWorkDate().startsWith(this.kintaiTotalPrintInput.getYearMonth())) {
                continue;
            }
            totalWorkMinutesMap.put(oneDayKintai.getWorkDate(), Integer.valueOf(oneDayKintai.getWorkMinutes()));
            totalOverWorkMinutesMap.put(oneDayKintai.getWorkDate(), Integer.valueOf(oneDayKintai.getOverWorkMinutes()));
        }

        Set<String> keySet = totalWorkMinutesMap.keySet();
        for(String key : keySet) {
            this.totalWorkMinutes += totalWorkMinutesMap.get(key);
            this.totalOverWorkMinutes += totalOverWorkMinutesMap.get(key);
        }
    }
}
