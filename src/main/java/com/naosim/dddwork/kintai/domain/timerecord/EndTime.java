package com.naosim.dddwork.kintai.domain.timerecord;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.Value;

import java.time.ZonedDateTime;

@Value
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class EndTime {

    ZonedTimePoint timePoint;

    public EndTime(ZonedDateTime date, String endTime) throws Exception {
        this.timePoint = new ZonedTimePoint(date, endTime);
    }
}
