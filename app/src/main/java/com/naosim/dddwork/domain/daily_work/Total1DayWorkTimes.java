package com.naosim.dddwork.domain.daily_work;

/**
 * 1日の実働時間
 */
public class Total1DayWorkTimes {
    private final ClockTimesDuration total1DayWorkTimes;

    public Total1DayWorkTimes(WorkTimeMoments workTimeMoments){
        total1DayWorkTimes = new ClockTimesDuration(workTimeMoments.getStartWorkTime(), workTimeMoments.getEndWorkTime());

        excludeBreakTimes(workTimeMoments);
    }

    public Total1DayWorkTimes(Total1DayWorkTimes workTimes) {
        total1DayWorkTimes = workTimes.total1DayWorkTimes;
    }

    private void excludeBreakTimes(WorkTimeMoments workMoments){
        ExcludeBreakTimesDomainService.excludeBreakTimes(workMoments.getEndWorkTime(), total1DayWorkTimes);
        /*
        for(BreakTimeMoments chunkBreakTimeMoments : new BreakTimeMomentsList().getBreakTimeMomentsList()){
            System.out.println("ここから");
            if(!checkExcludeBreakTimesNecessity(workMoments, chunkBreakTimeMoments)){
                System.out.println("除外計算不要");
                continue;
            }
            System.out.println("除外計算必要");
            if(!checkExcludeChunkTotalBreakTimes(workMoments, chunkBreakTimeMoments)) {
                total1DayWorkTimes.subtractTimeMomentsDifference(new TimeMomentsDifference(chunkBreakTimeMoments.getBreakTimeDifference()).getTimeMomentsDifference().toMinutes());
                System.out.println("60分除外必要");
                continue;
            }

            total1DayWorkTimes.subtractTimeMomentsDifference(new TimeMomentsDifference(chunkBreakTimeMoments.getStartBreakTime(),
                    workMoments.getEndWorkTime()).getTimeMomentsDifference().toMinutes());
        }*/
    }

    /*
    private boolean checkExcludeBreakTimesNecessity(WorkTimeMoments workTimeMoments, BreakTimeMoments breakTimeMoments){
        System.out.println("勤務終了" + workTimeMoments.getEndWorkTime().moment.getHour() + ":" + workTimeMoments.getEndWorkTime().moment.getMinute());
        System.out.println("休憩開始" + breakTimeMoments.getStartBreakTime().moment.getHour() + ":" + breakTimeMoments.getStartBreakTime().moment.getMinute());
        return new TimeMomentsDifference(workTimeMoments.getEndWorkTime(), breakTimeMoments.getStartBreakTime()).checkTimeMomentsHoursDifference();
    }

    private boolean checkExcludeChunkTotalBreakTimes(WorkTimeMoments workTimeMoments, BreakTimeMoments breakTimeMoments){
        return new TimeMomentsDifference(breakTimeMoments.getEndBreakTime(), workTimeMoments.getEndWorkTime()).checkTimeMomentsHoursDifference();
    }
     */

    public ClockTimesDuration getTotal1DayWorkTimes() {
        return new ClockTimesDuration(total1DayWorkTimes);
    }
}