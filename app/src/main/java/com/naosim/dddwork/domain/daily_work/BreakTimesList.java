package com.naosim.dddwork.domain.daily_work;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 休憩時刻の一覧
 */
public class BreakTimesList {
    /*
    private final List<BreakTimeMoments> breakTimeMomentsList = new ArrayList<>(Arrays.asList(
            new BreakTimeMoments(new ClockTime(12, 0), new ClockTime(13, 0)),
            new BreakTimeMoments(new ClockTime(18, 0), new ClockTime(19, 0)),
            new BreakTimeMoments(new ClockTime(21, 0), new ClockTime(22, 0))
            ));

    List<BreakTimeMoments> getBreakTimeMomentsList() {
        List<BreakTimeMoments> returnList = new ArrayList<>();

        for (BreakTimeMoments moments : breakTimeMomentsList){
            returnList.add(new BreakTimeMoments(moments));
        }

        return returnList;
    }
    */

    private final List<BreakTimes> breakTimeMomentsList = new ArrayList<>(Arrays.asList(
            new BreakTimes(new StartBreakTime(LocalTime.of(12, 0)), new EndBreakTime(LocalTime.of(13, 0))),
            new BreakTimes(new StartBreakTime(LocalTime.of(18, 0)), new EndBreakTime(LocalTime.of(19, 0))),
            new BreakTimes(new StartBreakTime(LocalTime.of(20, 0)), new EndBreakTime(LocalTime.of(21, 0)))
    ));

    List<BreakTimes> getBreakTimeMomentsList() {
        List<BreakTimes> returnList = new ArrayList<>();

        for (BreakTimes elements : breakTimeMomentsList){
            returnList.add(new BreakTimes(elements));
        }

        return returnList;
    }
}

class BreakTimes {
    private final StartBreakTime startBreakTime;
    private final EndBreakTime endBreakTime;

    BreakTimes(StartBreakTime startBreakTime, EndBreakTime endBreakTime) {
        this.startBreakTime = startBreakTime;
        this.endBreakTime = endBreakTime;
    }

    BreakTimes(BreakTimes elements) {
        this.startBreakTime = elements.startBreakTime;
        this.endBreakTime = elements.endBreakTime;
    }

    public StartBreakTime getStartBreakTime() {
        return startBreakTime;
    }

    public EndBreakTime getEndBreakTime() {
        return endBreakTime;
    }
}