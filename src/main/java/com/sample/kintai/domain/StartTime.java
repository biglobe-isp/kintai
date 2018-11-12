package com.sample.kintai.domain;

public class StartTime {
    private final Integer hour;
    private final Integer minute;

    public StartTime(String str) {
        this.hour = Integer.valueOf(str.substring(0, 2));
        this.minute = Integer.valueOf(str.substring(2, 4));
    }
    public int calcMinutes(){
        return hour * 60 + minute;
    }
}
