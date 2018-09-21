package com.naosim.dddwork.domain;

import lombok.Getter;

public class OvertimeHours {
    @Getter
    private final Integer value;

    public OvertimeHours (StartingHours startingHours, ClosingTime closingTime) {
        this.value = (((closingTime.getValue().getHour() * 60 ) + closingTime.getValue().getMinute())
                - ((startingHours.getValue().getHour()  * 60 ) + startingHours.getValue().getMinute())) - 480;
    }}
