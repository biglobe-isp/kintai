package kintai.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.List;

@RequiredArgsConstructor
@EqualsAndHashCode
@ToString(includeFieldNames = false)
public class Attendances {

    @Getter
    private final List<Attendance> values;

    public TotalWorkingTime totalWorkingTime() {
        return values.stream()
                .map(Attendance::totalWorkingTime)
                .reduce(TotalWorkingTime.ZERO, (sum, t) -> sum.plus(t));
    }
}
