package com.naosim.dddwork.kintai.domain.timerecord.attendance;

import com.naosim.dddwork.kintai.domain.timerecord.EndTime;
import com.naosim.dddwork.kintai.domain.timerecord.StartTime;
import com.naosim.dddwork.kintai.domain.timerecord.TimeInterval;
import com.naosim.dddwork.kintai.domain.timerecord.ZonedTimePoint;
import lombok.NonNull;
import lombok.Value;

@Value
public class AttendanceTimeInterval implements TimeInterval {
    @NonNull
    StartTime startTime;
    @NonNull
    EndTime endTime;


    public AttendanceTimeInterval(StartTime startTime, EndTime endTime) {
        if (startTime.getTimePoint().isAfter(endTime.getTimePoint())) {
            throw new IllegalStateException("開始時刻が終了時刻より後になっています。");
        }
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public ZonedTimePoint getStartTimePoint() {
        return this.startTime.getTimePoint();
    }

    @Override
    public ZonedTimePoint getEndTimePoint() {
        return this.endTime.getTimePoint();
    }

}
