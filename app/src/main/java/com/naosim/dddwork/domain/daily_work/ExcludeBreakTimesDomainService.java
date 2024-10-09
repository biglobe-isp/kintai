package com.naosim.dddwork.domain.daily_work;

public class ExcludeBreakTimesDomainService {
    public static void excludeBreakTimes(ClockTime endTime, ClockTimesDuration totalWorkTimes){
        for(BreakTimeMoments chunkBreakTimeMoments : new BreakTimeMomentsList().getBreakTimeMomentsList()){
            if(!checkExcludeBreakTimesNecessity(endTime, chunkBreakTimeMoments)) continue;
            if(checkExcludeChunkTotalBreakTimes(endTime, chunkBreakTimeMoments)) {
                totalWorkTimes.subtractTimeMomentsDifference(new ClockTimesDuration(chunkBreakTimeMoments.getBreakTimeDifference())
                        .getTimeMomentsDifference()
                        .toMinutes()); // 特に1文が長い場合は、methodごとに改行すると読みやすい
                continue;
            }

            totalWorkTimes.subtractTimeMomentsDifference(new ClockTimesDuration(chunkBreakTimeMoments.getStartBreakTime(), endTime)
                    .getTimeMomentsDifference()
                    .toMinutes());
        }
    }

    private static boolean checkExcludeBreakTimesNecessity(ClockTime endTime, BreakTimeMoments breakTimeMoments){
        return endTime.moment.isAfter(breakTimeMoments.getStartBreakTime().moment);
    }

    private static boolean checkExcludeChunkTotalBreakTimes(ClockTime endTime, BreakTimeMoments breakTimeMoments){
        return endTime.moment.isAfter(breakTimeMoments.getEndBreakTime().moment);
    }
}