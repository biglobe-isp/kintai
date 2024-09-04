package com.naosim.dddwork.domain;

class Total1DayWorkTimes {
    private final MinutesDifference total1DayWorkTimes;
    private final BreakTimeMomentsList BREAK_TIME_MOMENTS_LIST = new BreakTimeMomentsList();
    private final long ONE_HOUR_MINUTES = 60L;

    Total1DayWorkTimes(WorkTimeMoments workTimeMoments){
        total1DayWorkTimes = new MinutesDifference(workTimeMoments.getStartWorkTime(), workTimeMoments.getEndWorkTime());
    }

    private void ExcludeBreakTimes(WorkTimeMoments workMoments){
        for(BreakTimeMoments chunkBreakTimeMoments : BREAK_TIME_MOMENTS_LIST.getBreakTimeMomentsList()){
            if(CheckExcludeBreakTimesNecessity(workMoments, chunkBreakTimeMoments)) continue;
            if(CheckExcludeChunkBreakTimes(workMoments, chunkBreakTimeMoments)) {
                total1DayWorkTimes.SubtractMinutesDifference(new MinutesDifference(chunkBreakTimeMoments.getBreakTimeDifference()).GetMinutesDifference());
                continue;
            }

            total1DayWorkTimes.SubtractMinutesDifference(new MinutesDifference(chunkBreakTimeMoments.getStartBreakTime(), workMoments.getEndWorkTime()).GetMinutesDifference());
        }
    }

    private boolean CheckExcludeBreakTimesNecessity(WorkTimeMoments workTimeMoments, BreakTimeMoments breakTimeMoments){
        return new HoursDifference(breakTimeMoments.getStartBreakTime(), workTimeMoments.getEndWorkTime()).CheckHourDifference();
    }

    private boolean CheckExcludeChunkBreakTimes(WorkTimeMoments workTimeMoments, BreakTimeMoments breakTimeMoments){
        return new HoursDifference(workTimeMoments.getEndWorkTime(), breakTimeMoments.getEndBreakTime()).CheckHourDifference();
    }
}