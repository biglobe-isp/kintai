package jp.co.biglobe.isp.kintai.domain.work_record;

import io.vavr.collection.List;
import jp.co.biglobe.isp.kintai.domain.work_regulation.BreakTime;
import lombok.Value;


/**
 * 実働時間
 */
@Value
public class WorkRecordMinutes {
    int value;
    public WorkRecordMinutes(WorkTime workTime, List<BreakTime> breakTimeList) {
//        value = breakTimeList.foldLeft(workTime, TimeRange::subtract).getTimeSpanMinutes();
        var breakTimeMinutes = breakTimeList.map(x -> workTime.getBreakTimeSpanMinutes(x).getValue()).sum();
        value = workTime.getTimeSpanMinutes().getValue() - breakTimeMinutes.intValue();
    }
}
