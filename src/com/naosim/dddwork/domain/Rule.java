package com.naosim.dddwork.domain;

import java.security.acl.LastOwnerException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;

public class Rule {
    TimeSpan workingHourSpan;
    LocalDate validTermFrom;
    LocalDate validTermTo;
    TimeSpan[] rests;


    public Rule(TimeSpan workingHourSpan) {
        this(workingHourSpan, null, null);
    }

    public Rule(TimeSpan workingHourSpan, String validTermFrom, String validTermTo) {
        this.workingHourSpan = workingHourSpan;
        if (validTermFrom == null) {
            this.validTermFrom = LocalDate.MIN;
        } else {
            this.validTermFrom = DateTimeFormatter.ofPattern("uuuuMMdd")
                    .withResolverStyle(ResolverStyle.STRICT)
                    .parse(validTermFrom, LocalDate::from);
        }
        if (validTermTo == null) {
            this.validTermTo = LocalDate.MAX;
        } else {
            this.validTermTo = DateTimeFormatter.ofPattern("uuuuMMdd")
                    .withResolverStyle(ResolverStyle.STRICT)
                    .parse(validTermTo, LocalDate::from);
        }
    }

    public Rule(TimeSpan workingHourSpan, TimeSpan[] rests) {
        this(workingHourSpan);
        this.rests = rests;
    }

    public Rule(TimeSpan workingHourSpan, String validTermFrom, String validTermTo, TimeSpan[] rests) {
        this(workingHourSpan, validTermFrom, validTermTo);
        this.rests = rests;
    }

    public boolean isValid(LocalDate targetDate) {
        if (targetDate.isBefore(this.validTermFrom)) return false;
        if (targetDate.isAfter(this.validTermTo)) return false;
        return true;
    }

    public TimeSpan getWorkingHourSpan() {
        return this.workingHourSpan;
    }

    public TimeSpan[] getRests() {
        return this.rests;
    }

    public int getScheduledWorkingHours() {

        int workMinutes = this.workingHourSpan.getMinutes();
        for (TimeSpan rest : this.rests) {
            if (this.workingHourSpan.endTimeHour == rest.getStartTimeHour()) {
                workMinutes -= this.workingHourSpan.endTimeMinute;
            } else if (this.workingHourSpan.endTimeHour >= rest.getEndTimeHour()) {
                workMinutes -= 60;
            }
        }
        return workMinutes;
    }
}