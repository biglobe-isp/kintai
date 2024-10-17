package com.naosim.dddwork.domain.daily_work;

import java.time.Duration;
import java.time.LocalTime;

/**
 * 所定労働時間
 */
public class ScheduledWorkingMinutes {
    private final StartWorkTime SCHEDULED_START_TIME = new StartWorkTime(LocalTime.of(9, 0));
    private final EndWorkTime SCHEDULED_END_TIME = new EndWorkTime(LocalTime.of(18, 0));
    private final Duration scheduledWorkTimes = CalculateWorkingMinutesDomainService.calcWorkTimes(SCHEDULED_START_TIME, SCHEDULED_END_TIME);

    public Duration getScheduledWorkTimes() {
        return scheduledWorkTimes;
    }

    /*
    private void excludeBreakTimes(){
        CalculateWorkTimesDomainService.excludeBreakTimes(SCHEDULED_END_TIME, scheduledWorkTimes);
        /*
        for(BreakTimeMoments chunkBreakTimeMoments : new BreakTimeMomentsList().getBreakTimeMomentsList()){
            if(checkExcludeBreakTimesNecessity(chunkBreakTimeMoments)) continue;
            if(checkExcludeChunkBreakTimes(chunkBreakTimeMoments)) {
                scheduledWorkTimes.subtractTimeMomentsDifference(new TimeMomentsDifference(chunkBreakTimeMoments.getBreakTimeDifference()).getTimeMomentsDifference().toMinutes());
                continue;
            }

            scheduledWorkTimes.subtractTimeMomentsDifference(new TimeMomentsDifference(chunkBreakTimeMoments.getValue(),
                    this.SCHEDULED_END_TIME).getTimeMomentsDifference().toMinutes());
        }
    }


    private boolean checkExcludeBreakTimesNecessity(BreakTimeMoments breakTimeMoments){
        return new TimeMomentsDifference(breakTimeMoments.getValue(), SCHEDULED_END_TIME).checkTimeMomentsHoursDifference();
    }

    private boolean checkExcludeChunkBreakTimes(BreakTimeMoments breakTimeMoments){
        return new TimeMomentsDifference(SCHEDULED_END_TIME, breakTimeMoments.getValue()).checkTimeMomentsHoursDifference();
    }


    public ClockTimesDuration getScheduledWorkTimes() {
        return new ClockTimesDuration(scheduledWorkTimes);
    }
    */
}