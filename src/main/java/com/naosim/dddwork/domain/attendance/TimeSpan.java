package com.naosim.dddwork.domain.attendance;

import com.naosim.dddwork.utility.TimeUtility;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

@ToString(includeFieldNames = false)
@EqualsAndHashCode
@RequiredArgsConstructor
public class TimeSpan {

    @Getter
    private final LocalTime startTime;

    @Getter
    private final LocalTime endTime;

    /**
     * 期間が重複しているか
     *
     * @param other 対象の期間
     * @return 期間重複の真偽
     */
    public boolean overlap(TimeSpan other) {

        return !((other.getStartTime().isBefore(this.getStartTime()) &&
                other.getEndTime().isBefore(this.getStartTime())) ||
                (other.getStartTime().isAfter(this.getEndTime()) &&
                        other.getEndTime().isAfter(this.getEndTime())));
    }

    /**
     * 重複した期間の分を計算する
     *
     * @param other 対象の期間
     * @return 重複した期間の分
     */
    public int calcOverlapMinutes(TimeSpan other) {

        if (this.overlap(other)) {
            return (int) TimeUtility.lateTime(this.getStartTime(), other.getStartTime())
                    .until(TimeUtility.earlyTime(this.getEndTime(), other.getEndTime()), ChronoUnit.MINUTES);
        }
        return 0;
    }

    /**
     * StartTime〜EndTime間の分を計算する
     *
     * @return StartTime〜EndTime間の分
     */
    public int calcSpanTime() {

        return (int) this.getStartTime().until(this.getEndTime(), ChronoUnit.MINUTES);
    }
}
