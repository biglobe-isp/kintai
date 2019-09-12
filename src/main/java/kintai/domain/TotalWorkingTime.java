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
    private final WorkingDuration workingDuration;

    @Getter
    private final WorkingDuration overWorkingDuration;

    public static final TotalWorkingTime ZERO = new TotalWorkingTime(WorkingDuration.ZERO, WorkingDuration.ZERO);

    public TotalWorkingTime plus(TotalWorkingTime other) {
        return new TotalWorkingTime(workingDuration.plus(other.workingDuration), overWorkingDuration.plus(other.overWorkingDuration));
    }
}
