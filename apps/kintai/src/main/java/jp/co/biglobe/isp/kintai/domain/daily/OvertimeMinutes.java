package jp.co.biglobe.isp.kintai.domain.daily;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

public record OvertimeMinutes(int value) {
    /**
     * 残業か否かの境界値となる時間量.
     */
    private static final Duration OVERTIME_BORDER = Duration.of(8, ChronoUnit.HOURS);

    public static OvertimeMinutes create(WorkTimeMinutes workTimeMinutes) {
        return new OvertimeMinutes(
                workTimeMinutes.value() < OVERTIME_BORDER.toMinutes()
                        ? 0
                        : (int) (workTimeMinutes.value() - OVERTIME_BORDER.toMinutes())
        );
    }
}
