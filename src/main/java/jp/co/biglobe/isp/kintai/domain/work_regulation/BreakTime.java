package jp.co.biglobe.isp.kintai.domain.work_regulation;

import lombok.Value;

import java.time.LocalTime;

/**
 * 休憩時刻
 */
@Value
public class BreakTime {
    LocalTime BreakStartTime;
    LocalTime BreakEndTime;

    public BreakTime(LocalTime BreakStartTime, LocalTime BreakEndTime) {
        this.BreakStartTime = BreakStartTime;
        this.BreakEndTime   = BreakEndTime;
    }
}
