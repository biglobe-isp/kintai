package kintai.domain;

import lombok.*;

import java.time.Duration;

@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
public class EmployeeRule {
    @Getter
    private final BreakTimes breakTimes;
    @Getter
    private final Duration workHours;
}
