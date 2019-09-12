package kintai.domain;

import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.time.Duration;

@RequiredArgsConstructor
@EqualsAndHashCode
@ToString(includeFieldNames = false)
public class WorkingDuration {

    public static final WorkingDuration ZERO = new WorkingDuration(Duration.ZERO);

    private final Duration value;

    public static WorkingDuration ofMinutes(long minutes) {
        return new WorkingDuration(Duration.ofMinutes(minutes));
    }

    public WorkingDuration plus(WorkingDuration other) {
        return new WorkingDuration(value.plus(other.value));
    }

    public WorkingDuration overWorkingDuration(Duration standardWorkHours) {
        if (value.compareTo(standardWorkHours) < 1) {
            // 勤務時間が標準勤務時間より短い場合、残業時間は0時間
            return ZERO;
        }
        return new WorkingDuration(value.minus(standardWorkHours));
    }

    public long toMinutes() {
        return value.toMinutes();
    }

    public long toHours() {
        return value.toHours();
    }

    public long getMinutesInHour() {
        return value.toMinutes() % 60;
    }
}
