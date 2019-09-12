package kintai.domain;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@EqualsAndHashCode
@ToString(includeFieldNames = false)
public class BreakRanges {
    @Getter
    private final List<BreakRange> values;

    public static kintai.domain.BreakRanges.BreakRangesBuilder builder() {
        return new kintai.domain.BreakRanges.BreakRangesBuilder();
    }

    public static class BreakRangesBuilder {
        private List<BreakRange> values = new ArrayList<>();

        public kintai.domain.BreakRanges.BreakRangesBuilder append(int fromHour, int fromMinutes, int toHour, int toMinutes) {
            values.add(new BreakRange(LocalTime.of(fromHour, fromMinutes), LocalTime.of(toHour, toMinutes)));
            return this;
        }

        public BreakRanges build() {
            return new BreakRanges(values);
        }
    }
}
