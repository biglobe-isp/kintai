package com.naosim.dddwork.domain.daily_work;

import java.time.LocalTime;

/**
 * 時刻
 */
public class TimeMoment {
    LocalTime moment;

    TimeMoment(int hour, int minute){
        try {
            moment = LocalTime.of(hour, minute);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    TimeMoment(TimeMoment timeMoment){
        this.moment = timeMoment.moment;
    }
}