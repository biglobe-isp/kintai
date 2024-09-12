package com.naosim.dddwork.domain;

class Total1MonthNormalWorkTimes {
    private final Total1MonthTimeMoments totalNormalWorkTimes = new Total1MonthTimeMoments();

    Total1MonthNormalWorkTimes(Total1MonthWorkTimes workTimes, Total1MonthOverworkTimes overworkTimes) {
        calcTotalNormalWorkTimes(workTimes, overworkTimes);
    }

    private void calcTotalNormalWorkTimes(Total1MonthWorkTimes workTimes, Total1MonthOverworkTimes overworkTimes) {
        long adjustedNormalWorkTimes = workTimes.getTotal1MonthWorkTimes().GetTotalTimes() - overworkTimes.getTotalOverworkTimes().GetTotalTimes();
        totalNormalWorkTimes.AddTimes(adjustedNormalWorkTimes);
    }
}