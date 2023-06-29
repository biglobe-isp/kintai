package jp.co.biglobe.isp.kintai.domain.work_regulation;

import jp.co.biglobe.isp.kintai.domain.time_range.TimeRange;
import lombok.Value;

import java.time.LocalTime;

/**
 * 規定勤務時刻
 */
@Value
public class RegulatedWorkTime implements TimeRange {
    LocalTime regulatedOpeningTime;
    LocalTime regulatedClosingTime;

    public RegulatedWorkTime(LocalTime RegulatedOpeningTime, LocalTime RegulatedClosingTime) {
        this.regulatedOpeningTime = RegulatedOpeningTime;
        this.regulatedClosingTime = RegulatedClosingTime;
    }

    @Override
    public LocalTime getStartTime() {
        return regulatedOpeningTime;
    }

    @Override
    public LocalTime getEndTime() {
        return regulatedClosingTime;
    }
}
