package com.naosim.dddwork.domain;

class BreakTimeMoments {
    private final TimeMoment startBreakTime;
    private final TimeMoment endBreakTime;

    BreakTimeMoments(TimeMoment startBreakTime, TimeMoment endBreakTime) {
        this.startBreakTime = new TimeMoment(startBreakTime);

        this.endBreakTime = new TimeMoment(endBreakTime);
        if(ValidateEndBreakTime()) System.err.println("休憩終了時間が不正な値です。");
    }

    BreakTimeMoments(BreakTimeMoments moments){
        this.startBreakTime = moments.startBreakTime;
        this.endBreakTime = moments.endBreakTime;
    }

    private boolean ValidateEndBreakTime() {
        return new MinutesDifference(startBreakTime, endBreakTime).ValidateMinutesDifference();
    }

    TimeMoment getStartBreakTime() {
        return new TimeMoment(startBreakTime);
    }

    TimeMoment getEndBreakTime() {
        return new TimeMoment(endBreakTime);
    }

    MinutesDifference getBreakTimeDifference() {
        return new MinutesDifference(startBreakTime, endBreakTime);
    }
}