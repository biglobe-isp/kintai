package jp.co.biglobe.isp.kintai.domain.work_regulation;

import jp.co.biglobe.isp.kintai.domain.time_range.TimeRange;
import lombok.Value;

import java.time.LocalTime;

/**
 * 休憩時刻
 */
@Value
public class BreakTime implements TimeRange {
    LocalTime breakStartTime;
    LocalTime breakEndTime;

    public BreakTime(LocalTime breakStartTime, LocalTime breakEndTime) {
        this.breakStartTime = breakStartTime;
        this.breakEndTime = breakEndTime;
    }

    @Override
    public LocalTime getStartTime() {
        return breakStartTime;
    }

    @Override
    public LocalTime getEndTime() {
        return breakEndTime;
    }
}
