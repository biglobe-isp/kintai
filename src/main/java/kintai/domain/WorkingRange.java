package kintai.domain;

import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.time.Duration;
import java.time.LocalTime;
import java.util.Optional;

@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
public class WorkingRange {

    private final LocalTime from;
    private final LocalTime to;

    /**
     * intersect
     * 引数 other で指定された range と重なる範囲の range を返却する。
     * 重なる期間がない場合、空の Working Range を返却する
     * @param other
     * @return
     */
    public Optional<WorkingRange> intersect(WorkingRange other) {
        LocalTime from = this.from.isAfter(other.from) ? this.from : other.from;
        LocalTime to = this.to.isBefore(other.to) ? this.to : other.to;
        if (from.isAfter(to)) {
            return Optional.empty();
        }
        return Optional.of(new WorkingRange(from, to));
    }

    public Duration duration() {
        return Duration.between(from, to);
    }
}
