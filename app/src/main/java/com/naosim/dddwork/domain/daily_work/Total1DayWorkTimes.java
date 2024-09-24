package com.naosim.dddwork.domain.daily_work;

/**
 * 1日の実働時間
 */
public class Total1DayWorkTimes {
    private final TimeMomentsDifference total1DayWorkTimes;
    private final BreakTimeMomentsList BREAK_TIME_MOMENTS_LIST = new BreakTimeMomentsList();

    public Total1DayWorkTimes(WorkTimeMoments workTimeMoments){
        total1DayWorkTimes = new TimeMomentsDifference(workTimeMoments.getStartWorkTime(), workTimeMoments.getEndWorkTime());

        excludeBreakTimes(workTimeMoments);
    }

    public Total1DayWorkTimes(Total1DayWorkTimes workTimes) {
        total1DayWorkTimes = workTimes.total1DayWorkTimes;
    }

    private void excludeBreakTimes(WorkTimeMoments workMoments){
        for(BreakTimeMoments chunkBreakTimeMoments : BREAK_TIME_MOMENTS_LIST.getBreakTimeMomentsList()){
            if(checkExcludeBreakTimesNecessity(workMoments, chunkBreakTimeMoments)) continue;
            if(checkExcludeChunkBreakTimes(workMoments, chunkBreakTimeMoments)) {
                total1DayWorkTimes.subtractTimeMomentsDifference(new TimeMomentsDifference(chunkBreakTimeMoments.getBreakTimeDifference()).getTimeMomentsDifference());
                continue;
            }

            total1DayWorkTimes.subtractTimeMomentsDifference(new TimeMomentsDifference(chunkBreakTimeMoments.getStartBreakTime(),
                    workMoments.getEndWorkTime()).getTimeMomentsDifference());
        }
    }

    private boolean checkExcludeBreakTimesNecessity(WorkTimeMoments workTimeMoments, BreakTimeMoments breakTimeMoments){
        return new TimeMomentsDifference(breakTimeMoments.getStartBreakTime(), workTimeMoments.getEndWorkTime()).checkHourDifference();
    }

    private boolean checkExcludeChunkBreakTimes(WorkTimeMoments workTimeMoments, BreakTimeMoments breakTimeMoments){
        return new TimeMomentsDifference(workTimeMoments.getEndWorkTime(), breakTimeMoments.getEndBreakTime()).checkHourDifference();
    }

    public TimeMomentsDifference getTotal1DayWorkTimes() {
        return new TimeMomentsDifference(total1DayWorkTimes);
    }
}