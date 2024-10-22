package com.naosim.dddwork.domain;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;

public class WorkRule {
    private final LocalTime WORK_START_TIME = LocalTime.of(9,0);
    private final LocalTime WORK_END_TIME = LocalTime.of(18, 0);
    private final Integer MAX_DAILY_WORK_MINUTES = 480;
    private final ArrayList<RestTimeInfo> REST_TIME_INFO_ARRAY = new ArrayList<RestTimeInfo>(
            Arrays.asList(
                    new RestTimeInfo(LocalTime.of(12,0), LocalTime.of(13,0)),
                    new RestTimeInfo(LocalTime.of(18,0), LocalTime.of(19,0)),
                    new RestTimeInfo(LocalTime.of(21,0), LocalTime.of(22,0))
            )
    );
    private static class RestTimeInfo{
        public LocalTime startTime;
        public LocalTime endTime;
        RestTimeInfo(LocalTime s, LocalTime e){startTime = s;endTime = e;}
    }


    public Boolean isWorkInfoValid(LocalDate date, LocalTime startTime, LocalTime endTime){
        return !startTime.isAfter(WORK_START_TIME);
    }

    public WorkTimeSpecification getWorkTimeSpecification(LocalDate date, LocalTime startTime, LocalTime endTime){
        WorkTimeSpecification workTimeSpecification = new WorkTimeSpecification();

        return workTimeSpecification;
    }
}
