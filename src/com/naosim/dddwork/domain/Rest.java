package com.naosim.dddwork.domain;

public class Rest {
    private final static Hour lunchBreak = new Hour(12);
    private final static Hour eveningBreak = new Hour(18);
    private final static Hour nightBreak = new Hour(21);

    public static Hour getLunchBreak() {
        return lunchBreak;
    }

    public static Hour getEveningBreak() {
        return eveningBreak;
    }

    public static Hour getNightBreak() {
        return nightBreak;
    }
}
