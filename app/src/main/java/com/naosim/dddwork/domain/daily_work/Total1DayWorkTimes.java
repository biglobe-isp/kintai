package com.naosim.dddwork.domain.daily_work;

public class Total1DayWorkTimes {
    private final MinutesDifference total1DayWorkTimes;
    private final BreakTimeMomentsList BREAK_TIME_MOMENTS_LIST = new BreakTimeMomentsList();

    public Total1DayWorkTimes(WorkTimeMoments workTimeMoments){
        total1DayWorkTimes = new MinutesDifference(workTimeMoments.getStartWorkTime(), workTimeMoments.getEndWorkTime());

        excludeBreakTimes(workTimeMoments);
    }

    public Total1DayWorkTimes(Total1DayWorkTimes workTimes) {
        total1DayWorkTimes = workTimes.total1DayWorkTimes;
    }

    private void excludeBreakTimes(WorkTimeMoments workMoments){
        for(BreakTimeMoments chunkBreakTimeMoments : BREAK_TIME_MOMENTS_LIST.getBreakTimeMomentsList()){
            if(checkExcludeBreakTimesNecessity(workMoments, chunkBreakTimeMoments)) continue;
            if(checkExcludeChunkBreakTimes(workMoments, chunkBreakTimeMoments)) {
                total1DayWorkTimes.subtractMinutesDifference(new MinutesDifference(chunkBreakTimeMoments.getBreakTimeDifference())
                        .getMinutesDifference());
                continue;
            }

            total1DayWorkTimes.subtractMinutesDifference(new MinutesDifference(chunkBreakTimeMoments.getStartBreakTime(),
                    workMoments.getEndWorkTime()).getMinutesDifference());
        }
    }

    private boolean checkExcludeBreakTimesNecessity(WorkTimeMoments workTimeMoments, BreakTimeMoments breakTimeMoments){
        return new HoursDifference(breakTimeMoments.getStartBreakTime(), workTimeMoments.getEndWorkTime()).checkHourDifference();
    }

    private boolean checkExcludeChunkBreakTimes(WorkTimeMoments workTimeMoments, BreakTimeMoments breakTimeMoments){
        return new HoursDifference(workTimeMoments.getEndWorkTime(), breakTimeMoments.getEndBreakTime()).checkHourDifference();
    }

    public MinutesDifference getTotal1DayWorkTimes() {
        return new com.naosim.dddwork.domain.daily_work.MinutesDifference(total1DayWorkTimes);
    }
}