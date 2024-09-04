package com.naosim.dddwork.domain;

import java.util.ArrayList;
import java.util.List;

class BreakTimeMomentsList {
    private final List<BreakTimeMoments> breakTimeMomentsList;

    BreakTimeMomentsList() {
        breakTimeMomentsList = new ArrayList<BreakTimeMoments>();

        Register();
    }

    private void Register(){
        breakTimeMomentsList.add(new BreakTimeMoments(12, 0, 13, 0));
        breakTimeMomentsList.add(new BreakTimeMoments(18, 0, 19, 0));
        breakTimeMomentsList.add(new BreakTimeMoments(21, 0, 22, 0));
    }

    List<BreakTimeMoments> getBreakTimeMomentsList() {
        List<BreakTimeMoments> returnList = new ArrayList<BreakTimeMoments>();

        for (BreakTimeMoments moments : breakTimeMomentsList){
            returnList.add(new BreakTimeMoments(moments));
        }

        return returnList;
    }
}