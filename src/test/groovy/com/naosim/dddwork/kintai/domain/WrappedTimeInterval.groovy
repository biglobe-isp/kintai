package com.naosim.dddwork.kintai.domain


import com.naosim.dddwork.kintai.domain.timerecord.TimeInterval

class WrappedTimeInterval {
    static TimeInterval get(startTime, endTime) {
        new TimeInterval(WrappedStartTime.get(startTime), WrappedEndTime.get(endTime))
    }
}
