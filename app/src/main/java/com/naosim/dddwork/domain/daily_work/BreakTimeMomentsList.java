package com.naosim.dddwork.domain.daily_work;

import java.util.ArrayList;
import java.util.List;

class BreakTimeMomentsList {
    private final List<BreakTimeMoments> breakTimeMomentsList;

    BreakTimeMomentsList() {
        breakTimeMomentsList = new ArrayList<>();

        register();
    }

    private void register(){
        breakTimeMomentsList.add(new BreakTimeMoments(new TimeMoment(12, 0), new TimeMoment(13, 0)));
        breakTimeMomentsList.add(new BreakTimeMoments(new TimeMoment(18, 0), new TimeMoment(19, 0)));
        breakTimeMomentsList.add(new BreakTimeMoments(new TimeMoment(21, 0), new TimeMoment(22, 0)));
    }

    List<BreakTimeMoments> getBreakTimeMomentsList() {
        List<BreakTimeMoments> returnList = new ArrayList<>();

        for (BreakTimeMoments moments : breakTimeMomentsList){
            returnList.add(new BreakTimeMoments(moments));
        }

        return returnList;
    }
}