package kintai.domain;

import java.time.Duration;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@EqualsAndHashCode
@ToString(includeFieldNames = false)
public class BreakTimes {
    private final List<WorkingRange> values;

    /**
     * workingRange と重なる（含まれる）範囲の BreakTimes を返却する
     * @param workingRange
     * @return
     */
    public BreakTimes intersect(WorkingRange workingRange) {
        List<WorkingRange> intersectValues = values.stream()
                .map(r -> r.intersect(workingRange))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
        return new BreakTimes(intersectValues);
    }

    public Duration totalBreakTime(WorkingRange workingRange) {
        List<WorkingRange> intersectValues = values.stream()
                .map(r -> r.intersect(workingRange))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
        Duration total = intersectValues.stream()
            .map(WorkingRange::duration)
            .reduce(Duration.ZERO, (sum, d) -> sum.plus(d));
        return total;
    }
}
