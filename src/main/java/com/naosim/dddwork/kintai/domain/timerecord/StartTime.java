package com.naosim.dddwork.kintai.domain.timerecord;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.Value;

import java.time.ZonedDateTime;

@Value
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class StartTime {

    ZonedTimePoint timePoint;

    public StartTime(ZonedDateTime date, String startTime) throws Exception {
        this.timePoint = new ZonedTimePoint(date, startTime);
    }
}
