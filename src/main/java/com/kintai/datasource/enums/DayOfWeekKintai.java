package com.kintai.datasource.enums;

public enum DayOfWeekKintai {
    MONDAY(0, "Monday"),
    TUESDAY(1, "Tuesday"),
    WEDNESDAY(2, "Wednesday"),
    THURSDAY(3, "Thursday"),
    FRIDAY(4, "Friday"),
    SATURDAY(5, "Saturday"),
    SUNDAY(6, "Sunday");

    private final int dayValue;
    private final String dayOfName;

    DayOfWeekKintai(int dayValue, String dayOfName) {
        this.dayValue = dayValue;
        this.dayOfName = dayOfName;
    }

    public int getDayValue() {
        return this.dayValue;
    }

    public String getDayOfName() {
        return this.dayOfName;
    }

}
