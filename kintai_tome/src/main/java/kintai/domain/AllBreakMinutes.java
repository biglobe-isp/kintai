package kintai.domain;

import kintai.domain.company.BreakEndTime;
import kintai.domain.company.BreakStartTime;

public class AllBreakMinutes {

    private int value;

    public AllBreakMinutes(int value) {
        this.value = value;
    }

    static AllBreakMinutes create(TaikinTime taikinTime) {
        int breakMinutes = getLunchBreakTime(taikinTime) + getEveningBreakTime(taikinTime) + getNightBreakTime(taikinTime);
        return new AllBreakMinutes(breakMinutes);
    }

    public int getValue() {
        return value;
    }

    private static int getLunchBreakTime(TaikinTime taikinTime) {

        if (taikinTime.getLocalTimeValue().getHour() == BreakStartTime.LunchBreak.getBreakTime()) {
            return taikinTime.getLocalTimeValue().getMinute();
        } else if (taikinTime.getLocalTimeValue().getHour() >= BreakEndTime.LunchBreak.getBreakTime()) {
            return 60;
        } else {
            return 0;
        }
    }

    private static int getEveningBreakTime(TaikinTime taikinTime) {

        if (taikinTime.getLocalTimeValue().getHour() == BreakStartTime.EveningBreak.getBreakTime()) {
            return taikinTime.getLocalTimeValue().getMinute();
        } else if (taikinTime.getLocalTimeValue().getHour() >= BreakEndTime.EveningBreak.getBreakTime()) {
            return 60;
        } else {
            return 0;
        }
    }


    private static int getNightBreakTime(TaikinTime taikinTime) {

        if (taikinTime.getLocalTimeValue().getHour() == BreakStartTime.NightBreak.getBreakTime()) {
            return taikinTime.getLocalTimeValue().getMinute();
        } else if (taikinTime.getLocalTimeValue().getHour() >= BreakEndTime.NightBreak.getBreakTime()) {
            return 60;
        } else {
            return 0;
        }

    }

}
