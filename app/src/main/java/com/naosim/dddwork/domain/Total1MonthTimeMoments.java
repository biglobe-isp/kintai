package com.naosim.dddwork.domain;

class Total1MonthTimeMoments {
    private long total1MonthTimeMoments = 0L;

    Total1MonthTimeMoments() {
    }

    Total1MonthTimeMoments(Total1MonthTimeMoments total1MonthWorkTimes){
        this.total1MonthTimeMoments = total1MonthWorkTimes.total1MonthTimeMoments;
    }

    void AddTimes(long times){
        total1MonthTimeMoments += times;
    }

    long GetTotalTimes() {
        return total1MonthTimeMoments;
    }
}