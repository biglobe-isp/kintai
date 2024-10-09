package com.naosim.dddwork.domain.daily_work;

/**
 * 所定労働時間
 */
public class ScheduledWorkTimes {
    private final ClockTime SCHEDULED_START_TIME = new ClockTime(9, 0);
    private final ClockTime SCHEDULED_END_TIME = new ClockTime(18, 0);
    private final ClockTimesDuration scheduledWorkTimes = new ClockTimesDuration(SCHEDULED_START_TIME, SCHEDULED_END_TIME);

    public ScheduledWorkTimes() {
        excludeBreakTimes();
    }


    private void excludeBreakTimes(){
        ExcludeBreakTimesDomainService.excludeBreakTimes(SCHEDULED_END_TIME, scheduledWorkTimes);
        /*
        for(BreakTimeMoments chunkBreakTimeMoments : new BreakTimeMomentsList().getBreakTimeMomentsList()){
            if(checkExcludeBreakTimesNecessity(chunkBreakTimeMoments)) continue;
            if(checkExcludeChunkBreakTimes(chunkBreakTimeMoments)) {
                scheduledWorkTimes.subtractTimeMomentsDifference(new TimeMomentsDifference(chunkBreakTimeMoments.getBreakTimeDifference()).getTimeMomentsDifference().toMinutes());
                continue;
            }

            scheduledWorkTimes.subtractTimeMomentsDifference(new TimeMomentsDifference(chunkBreakTimeMoments.getStartBreakTime(),
                    this.SCHEDULED_END_TIME).getTimeMomentsDifference().toMinutes());
        }
        */
    }

    /*
    private boolean checkExcludeBreakTimesNecessity(BreakTimeMoments breakTimeMoments){
        return new TimeMomentsDifference(breakTimeMoments.getStartBreakTime(), SCHEDULED_END_TIME).checkTimeMomentsHoursDifference();
    }

    private boolean checkExcludeChunkBreakTimes(BreakTimeMoments breakTimeMoments){
        return new TimeMomentsDifference(SCHEDULED_END_TIME, breakTimeMoments.getEndBreakTime()).checkTimeMomentsHoursDifference();
    }
    */

    public ClockTimesDuration getScheduledWorkTimes() {
        return new ClockTimesDuration(scheduledWorkTimes);
    }

    ClockTime getScheduledStartTime() {
        return new ClockTime(SCHEDULED_START_TIME);
    }
}