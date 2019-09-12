package kintai.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.time.LocalTime;

@RequiredArgsConstructor
@EqualsAndHashCode
@ToString(includeFieldNames = false)
public class BreakRange {

    @Getter
    private final TimeRange value;

    public BreakRange(LocalTime from, LocalTime to) {
        this.value = new TimeRange(from, to);
    }
}
