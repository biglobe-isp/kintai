package jp.co.biglobe.isp.kintai.domain.work_record;

import jp.co.biglobe.isp.kintai.domain.time_range.TimeRange;
import lombok.Value;

import java.time.LocalTime;

/**
 * 勤務時刻
 */
@Value
public class WorkTime implements TimeRange {
    LocalTime openingTime;
    LocalTime closingTime;

    public WorkTime(LocalTime openingTime, LocalTime closingTime){
        validate(openingTime, closingTime);
        this.openingTime = openingTime;
        this.closingTime = closingTime;
    }

    private void validate(LocalTime openingTime, LocalTime closingTime) {
        if(openingTime.isAfter(closingTime)) {
            throw new IllegalArgumentException("勤務開始時刻は勤務終了時刻より早い時間に設定してください.");
        }
        if(openingTime.isAfter(LocalTime.of(9,0))) {
            throw new IllegalArgumentException("勤務開始時刻は9:00より前に設定してください. でないとクビです.");
        }
    }

    @Override
    public LocalTime getStartTime() {
        return openingTime;
    }

    @Override
    public LocalTime getEndTime() {
        return closingTime;
    }
}
