package com.naosim.dddwork.kintai.domain.core.type.time.range;

import com.naosim.dddwork.kintai.domain.model.foundation.time.BeginTime;
import com.naosim.dddwork.kintai.domain.model.foundation.time.EndTime;
import lombok.Getter;
import lombok.ToString;


/**
 * 時間帯
 */
@ToString
@Getter
public class TimeRange {

    final BeginTime beginTime;
    final EndTime endTime;


    public static TimeRange of(BeginTime beginTime, EndTime endTime) {
        return new TimeRange(beginTime, endTime);
    }

    public TimeRange(BeginTime beginTime, EndTime endTime) {

        this.beginTime = beginTime;
        this.endTime = endTime;
    }
}
