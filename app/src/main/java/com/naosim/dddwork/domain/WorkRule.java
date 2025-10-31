package com.naosim.dddwork.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WorkRule {
    private static final int regularWorkingTime = 8;
    private static List<Integer> restHourList = new ArrayList<Integer>(Arrays.asList(12, 15, 18, 21));

    public static int getRegularWorkingTime() {
        return regularWorkingTime;
    }

    public static List<Integer> getRestHourList() {
        return restHourList;
    }
}
