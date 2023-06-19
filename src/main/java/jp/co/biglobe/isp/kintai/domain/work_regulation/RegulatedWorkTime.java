package jp.co.biglobe.isp.kintai.domain.work_regulation;

import lombok.Value;

import java.time.LocalTime;

/**
 * 規定勤務時刻
 */
@Value
public class RegulatedWorkTime {
    LocalTime regulatedOpeningTime;
    LocalTime regulatedClosingTime;

    public RegulatedWorkTime(LocalTime RegulatedOpeningTime, LocalTime RegulatedClosingTime) {
        this.regulatedOpeningTime = RegulatedOpeningTime;
        this.regulatedClosingTime = RegulatedClosingTime;
    }
}
