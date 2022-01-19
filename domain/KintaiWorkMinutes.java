package com.naosim.dddwork.domain;

//値オブジェクト
public class KintaiWorkMinutes {
    private int value;
    private static final int MINUTES_PER_HOUR = 60;
    private static final int REST_TIME_MINUTES = 60;
    private static final int REST_INCREASE_START_DATE = 20220215;

    public KintaiWorkMinutes(KintaiDate kintaiDate, KintaiStart kintaiStart, KintaiEnd kintaiEnd) {
        String start = kintaiStart.getValue();
        String end = kintaiEnd.getValue();

        int startH = Integer.valueOf(start.substring(0, 2));
        int startM = Integer.valueOf(start.substring(2, 4));

        int endH = Integer.valueOf(end.substring(0, 2));
        int endM = Integer.valueOf(end.substring(2, 4));

        int workMinutes = endH * MINUTES_PER_HOUR + endM - (startH * MINUTES_PER_HOUR + startM);

        if (endH == 12) {
            workMinutes -= endM;
        } else if (endH >= 13) {
            workMinutes -= REST_TIME_MINUTES;
        }

        if (Integer.valueOf(kintaiDate.getValue()) >= REST_INCREASE_START_DATE) {
            if (endH == 15) {
                workMinutes -= endM;
            } else if (endH >= 16) {
                workMinutes -= REST_TIME_MINUTES;
            }
        }

        if (endH == 18) {
            workMinutes -= endM;
        } else if (endH >= 19) {
            workMinutes -= REST_TIME_MINUTES;
        }

        if (endH == 21) {
            workMinutes -= endM;
        } else if (endH >= 22) {
            workMinutes -= REST_TIME_MINUTES;
        }

        this.value = workMinutes;
    }

    public int getValue() {
        return this.value;
    }

}
