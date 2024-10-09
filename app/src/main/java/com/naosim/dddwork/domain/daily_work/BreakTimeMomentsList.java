package com.naosim.dddwork.domain.daily_work;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 休憩時刻の一覧
 */
public class BreakTimeMomentsList {
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
}