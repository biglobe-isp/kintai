package com.naosim.dddwork.domain.daily_work;

import java.time.Duration;

/**
 * 労働時間計算ドメインサービス
 */
public class CalculateWorkingMinutesDomainService {
    public static Duration calcWorkTimes(StartWorkTime startWorkTime, EndWorkTime endWorkTime) {
        Duration workTimes = Duration.between(startWorkTime.getValue(), endWorkTime.getValue());

        if(validateWorkTimes(startWorkTime, endWorkTime)) throw new IllegalArgumentException("勤務終了時刻が勤務開始時刻よりも前に設定されています");

        return excludeBreakTimes(workTimes, endWorkTime);
    }

    private static boolean validateWorkTimes(StartWorkTime startWorkTime, EndWorkTime endWorkTime) {
        return Duration.between(startWorkTime.getValue(), endWorkTime.getValue()).toMinutes() < 0L;
    }

    public static Duration excludeBreakTimes(Duration totalWorkTimes, EndWorkTime endWorkTime){
        for(BreakTimes chunkBreakTimes : new BreakTimesList().getBreakTimeMomentsList()){
            if(!checkExcludeBreakTimesNecessity(endWorkTime, chunkBreakTimes.getStartBreakTime())) continue;
            if(checkExcludeChunkTotalBreakTimes(endWorkTime, chunkBreakTimes.getEndBreakTime())) {
                return subtractTimeMomentsDifference(totalWorkTimes,
                        Duration.between(chunkBreakTimes.getStartBreakTime().getValue(),
                                chunkBreakTimes.getEndBreakTime().getValue())
                                .toMinutes());
            }

            return subtractTimeMomentsDifference(totalWorkTimes,
                    Duration.between(chunkBreakTimes.getStartBreakTime().getValue(),
                            endWorkTime.getValue()).
                            toMinutes());
        }

        return totalWorkTimes;
    }

    private static boolean checkExcludeBreakTimesNecessity(EndWorkTime endWorkTime,
                                                           StartBreakTime startBreakTime){
        return endWorkTime.getValue()
                .isAfter(startBreakTime.getValue());
    }

    private static boolean checkExcludeChunkTotalBreakTimes(EndWorkTime endWorkTime,
                                                            EndBreakTime endBreakTime){
        return endWorkTime.getValue().
                isAfter(endBreakTime.getValue());
    }

    private static Duration subtractTimeMomentsDifference(Duration workTimes, long minutes) {
        return workTimes.minusMinutes(minutes);
    }
}