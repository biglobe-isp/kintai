package com.naosim.dddwork.domain;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.List;

public class Rule {
    private final RuleId ruleId;
    private final TimeSpan workingHourSpan;
    private final LocalDate validTermFrom;
    private final LocalDate validTermTo;
    private final List<TimeSpan> rests;

    public Rule(
            String ruleId,
            TimeSpan workingHourSpan,
            String validTermFrom,
            String validTermTo,
            List<TimeSpan> rests) {
        this.ruleId = new RuleId(ruleId);
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
        this.rests = rests;
    }

    public boolean isValid(LocalDate targetDate) {
        if (targetDate.isBefore(this.validTermFrom)) return false;
        return !targetDate.isAfter(this.validTermTo);
    }

    public TimeSpan getWorkingHourSpan() {
        return this.workingHourSpan;
    }

    public List<TimeSpan> getRests() {
        return this.rests;
    }

    public int getScheduledWorkingHours() {

        int workMinutes = this.workingHourSpan.getMinutes();
        for (TimeSpan rest : this.rests) {
            if (this.workingHourSpan.getEndTimeHour() == rest.getStartTimeHour()) {
                workMinutes -= this.workingHourSpan.getEndTimeMinute();
            } else if (this.workingHourSpan.getEndTimeHour() >= rest.getEndTimeHour()) {
                workMinutes -= 60;
            }
        }
        return workMinutes;
    }

    public RuleId getRuleId() {
        return this.ruleId;
    }
}