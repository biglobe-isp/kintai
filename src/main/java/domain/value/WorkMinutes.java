package domain.value;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public final class WorkMinutes {
    private final int workMinutes;

    WorkMinutes(int workMinutes) {
        if (workMinutes < 0) {
            throw new IllegalArgumentException("勤務時間は0分以上である必要があります。");
        }

        this.workMinutes = workMinutes;
    }

    public int getValue() {
        return this.workMinutes;
    }
}
