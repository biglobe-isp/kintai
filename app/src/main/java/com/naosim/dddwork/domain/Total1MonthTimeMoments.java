package com.naosim.dddwork.domain;

import java.time.Duration;

/**
 * 1か月の合計時間
 */
class Total1MonthTimeMoments {
    private Duration total1MonthTimeMoments = Duration.ZERO;

    Total1MonthTimeMoments() {
    }

    Total1MonthTimeMoments(Total1MonthTimeMoments total1MonthWorkTimes){
        this.total1MonthTimeMoments = total1MonthWorkTimes.total1MonthTimeMoments;
    }

    void AddTimes(Duration times){
        total1MonthTimeMoments = total1MonthTimeMoments.plusMinutes(times.toMinutes());
    }

    Duration GetTotalTimes() {
        return total1MonthTimeMoments;
    }
}