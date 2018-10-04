package jp.co.biglobe.kintai.domain.breaktime;

import jp.co.biglobe.kintai.util.Time;

public class BreakTime {

    private final Time startBreakTime;

    private final Time endBreakTime;

    public BreakTime(Time startTime,Time endTime){
        startBreakTime = startTime;
        endBreakTime = endTime;
    }

    public Time getStartBreakTime() {
        return startBreakTime;
    }

    public Time getEndBreakTime() {
        return endBreakTime;
    }
}
