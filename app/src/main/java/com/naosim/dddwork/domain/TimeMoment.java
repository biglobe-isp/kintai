package com.naosim.dddwork.domain;

import java.time.LocalTime;

class TimeMoment {
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