package kintai.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.time.Duration;

/**
 * 勤務時間、残業時間を表現するクラス。
 */
@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
public class TotalWorkingTime {

    @Getter
    private final Duration workingTime;

    @Getter
    private final Duration overWorkingTime;

    public static final TotalWorkingTime ZERO = new TotalWorkingTime(Duration.ZERO, Duration.ZERO);

    public TotalWorkingTime plus(TotalWorkingTime other) {
        return new TotalWorkingTime(this.workingTime.plus(other.workingTime), this.overWorkingTime.plus(other.overWorkingTime));
    }
}
