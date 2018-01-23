package com.naosim.dddwork.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@EqualsAndHashCode(callSuper = false)
@ToString
public class KintaiTotal {

    @Getter
    KintaiTotalPrintInput kintaiTotalPrintInput;

    @Getter
    // TODO: Listではなく、コレクションオブジェクトにする
    private List<String> registLineList;

    @Getter
    private int totalWorkMinutes;

    @Getter
    private int totalOverWorkMinutes;

    public KintaiTotal(KintaiTotalPrintInput kintaiTotalPrintInput, List<String> registLineList) {
        this.kintaiTotalPrintInput = kintaiTotalPrintInput;
        this.registLineList = registLineList;
        this.setTotalData();
    }

    private void setTotalData() {
        this.totalWorkMinutes = 0;
        this.totalOverWorkMinutes = 0;

        Map<String, Integer> totalWorkMinutesMap = new HashMap<>();
        Map<String, Integer> totalOverWorkMinutesMap = new HashMap<>();

        for (String line : registLineList) {

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
