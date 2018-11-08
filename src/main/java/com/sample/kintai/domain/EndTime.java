package com.sample.kintai.domain;

public class EndTime {
    private final Integer hour;
    private final Integer minute;

    public EndTime(String str) {
        this.hour = Integer.valueOf(str.substring(0, 2));
        this.minute = Integer.valueOf(str.substring(2, 4));
    }

    public int calcMinites(){
        return hour * 60 + minute;
    }

    public Integer getHour() {
        return hour;
    }
    public Integer getMinute() {
        return minute;
    }
}
