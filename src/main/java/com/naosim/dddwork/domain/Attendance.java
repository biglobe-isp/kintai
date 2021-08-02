package com.naosim.dddwork.domain;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Attendance {

    private final String date;
    private final String start;
    private final String end;
    private final LocalDate targetDate;
    private final TimeSpan time;
    private final int workMinutes, orverWorkMinutes;
    private final Rule rule;

    public Attendance(String date, String start, String end) {
        System.out.println(String.format("domain/Attendance params: date=%s start=%s end=%s", date, start, end));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        this.date = date;
        this.start = start;
        this.end = end;
        this.targetDate = LocalDate.parse(date, formatter);
        this.time = new TimeSpan(start, end);
        this.workMinutes = this.time.getMinutes();
        this.orverWorkMinutes = 0;
        this.rule = null;
    }

    private Attendance(String date, String start, String end, Rule rule) {
        this.date = date;
        this.start = start;
        this.end = end;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        this.targetDate = LocalDate.parse(date, formatter);
        this.time = new TimeSpan(start, end);

        // 就業規則を適用しない稼働時間を再算出
        int workMinutes = this.time.getMinutes();
        // 休憩時間を稼働時間から差し引く
        List<TimeSpan> rests = rule.getRests();
        for (TimeSpan rest : rests) {
            if (this.time.getEndTimeHour() == rest.getStartTimeHour()) {
                workMinutes -= this.time.getEndTimeMinute();
            } else if (this.time.getEndTimeHour() >= rest.getEndTimeHour()) {
                workMinutes -= 60;
            }
        }
        this.workMinutes = workMinutes;

        // 残業時間を算出
        this.orverWorkMinutes = Math.max(workMinutes - rule.getScheduledWorkingHours(), 0);

        this.rule = rule;
    }

    public Attendance applyRule(Rule rule) {
        return new Attendance(this.date, this.start, this.end, rule);
    }

    public LocalDate getTargetDate(){
        return this.targetDate;
    }

    public int getWorkMinutes() {
        return this.workMinutes;
    }
    public int getOrverWorkMinutes() {
        return this.orverWorkMinutes;
    }
    public Rule getRule() { return  this.rule;}
}