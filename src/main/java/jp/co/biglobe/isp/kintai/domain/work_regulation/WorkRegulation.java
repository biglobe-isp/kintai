package jp.co.biglobe.isp.kintai.domain.work_regulation;

import io.vavr.collection.List;
import lombok.Value;


/**
 * 就業規則
 */
@Value
public class WorkRegulation {
    int regulatedWorkMinutes;
    List<BreakTime> breakTimeList;

    public WorkRegulation (RegulatedWorkTime regulatedWorkTime, List<BreakTime> breakTimeList) {
        var breakTimeMinutes = breakTimeList.map(x -> regulatedWorkTime.getBreakTimeSpanMinutes(x).getValue()).sum();
        this.regulatedWorkMinutes = regulatedWorkTime.getTimeSpanMinutes().getValue() - breakTimeMinutes.intValue();
        this.breakTimeList= breakTimeList;
    }
}
