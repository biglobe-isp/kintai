package kintai.domain;

import lombok.Value;

@Value
public class WorkMinutes {
    int WorkMinutes;

    public static int calcWorkMinutes(int startH, int startM, int endH, int endM, int restTimeMinutes) {
        int workM = endH * 60 + endM  -(startH * 60 + startM) - restTimeMinutes;
        return workM;
    }


}
