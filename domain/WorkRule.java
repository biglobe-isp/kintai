package com.naosim.dddwork.domain;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;

public class WorkRule {
    private final LocalTime WORK_START_TIME = LocalTime.of(9,0);
    private final LocalTime WORK_END_TIME = LocalTime.of(18, 0);
    private final Integer MAX_DAILY_WORK_MINUTES = 480;
    private final HashMap<LocalTime, LocalTime> REST_TIME_MAP = new HashMap<LocalTime, LocalTime>(){
        {
            put(LocalTime.of(12,0), LocalTime.of(13,0));
            put(LocalTime.of(18,0), LocalTime.of(19,0));
            put(LocalTime.of(21,0), LocalTime.of(22,0));
        }
    };

    public Boolean isWorkInfoValid(LocalDate date, LocalTime startTime, LocalTime endTime){
        return !startTime.isAfter(WORK_START_TIME);
    }

    public WorkTimeSpecification getWorkTimeSpecification(LocalDate date, LocalTime startTime, LocalTime endTime){
        WorkTimeSpecification workTimeSpecification = new WorkTimeSpecification();

        Long workTime = ChronoUnit.MINUTES.between(startTime, endTime);

        //休憩時間を引く
        for(LocalTime s : REST_TIME_MAP.keySet()){
            LocalTime e = REST_TIME_MAP.get(s);
            if(endTime.isAfter(s) && startTime.isBefore(e)){
                workTime -= ChronoUnit.MINUTES.between(
                        startTime.isAfter(s) ? startTime : s,
                        endTime.isBefore(e) ? endTime : e
                );
            }
        }
        workTimeSpecification.workMinutes = workTime.intValue();
        workTimeSpecification.overWorkMinutes = workTime > MAX_DAILY_WORK_MINUTES ? workTime.intValue() - MAX_DAILY_WORK_MINUTES : 0;

        return workTimeSpecification;
    }
}
