package com.naosim.dddwork.domain.daily_work;

/**
 * 休憩時刻
 */
class BreakTimeMoments {
    private final ClockTime startBreakTime;
    private final ClockTime endBreakTime;

    BreakTimeMoments(ClockTime startBreakTime, ClockTime endBreakTime) {
        this.startBreakTime = new ClockTime(startBreakTime);
        this.endBreakTime = new ClockTime(endBreakTime);
    }

    BreakTimeMoments(BreakTimeMoments moments){
        this.startBreakTime = moments.startBreakTime;
        this.endBreakTime = moments.endBreakTime;
    }

    ClockTime getStartBreakTime() {
        return new ClockTime(startBreakTime);
    }

    ClockTime getEndBreakTime() {
        return new ClockTime(endBreakTime);
    }

    ClockTimesDuration getBreakTimeDifference() {
        return new ClockTimesDuration(startBreakTime, endBreakTime);
    }
}