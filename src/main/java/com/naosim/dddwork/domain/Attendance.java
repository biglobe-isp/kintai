package com.naosim.dddwork.domain;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Attendance {
    private final String yyyymmdd;
    private final String starthhmm;
    private final String endhhmm;
    private final LocalDate targetDate;
    private final TimeSpan time;
    private final int workMinutes, orverWorkMinutes;
    private final Rule rule;

    public Attendance(String yyyymmdd, String starthhmm, String endhhmm) {
        System.out.println(String.format("domain/Attendance params: yyyymmdd=%s starthhmm=%s endhhmm=%s", yyyymmdd,
                                         starthhmm, endhhmm
        ));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        this.yyyymmdd = yyyymmdd;
        this.starthhmm = starthhmm;
        this.endhhmm = endhhmm;
        this.targetDate = LocalDate.parse(yyyymmdd, formatter);
        this.time = new TimeSpan(starthhmm, endhhmm);
        this.workMinutes = this.time.getMinutes();
        this.orverWorkMinutes = 0;
        this.rule = null;
    }

    private Attendance(String yyyymmdd, String starthhmm, String endhhmm, Rule rule) {
        this.yyyymmdd = yyyymmdd;
        this.starthhmm = starthhmm;
        this.endhhmm = endhhmm;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        this.targetDate = LocalDate.parse(yyyymmdd, formatter);
        this.time = new TimeSpan(starthhmm, endhhmm);

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

    public Attendance(
            String yyyymmdd,
            String starthhmm,
            String endhhmm,
            int workMinutes,
            int orverWorkMinutes) {
        this.yyyymmdd = yyyymmdd;
        this.starthhmm = starthhmm;
        this.endhhmm = endhhmm;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        this.targetDate = LocalDate.parse(yyyymmdd, formatter);
        this.time = new TimeSpan(starthhmm, endhhmm);
        this.workMinutes = workMinutes;
        this.orverWorkMinutes = orverWorkMinutes;
        this.rule = null;
    }

    public Attendance applyRule(Rule rule) {
        return new Attendance(this.yyyymmdd, this.starthhmm, this.endhhmm, rule);
    }

    public LocalDate getTargetDate() {
        return this.targetDate;
    }

    public int getWorkMinutes() {
        return this.workMinutes;
    }

    public int getOrverWorkMinutes() {
        return this.orverWorkMinutes;
    }

    public String getYyyymmdd() {
        return this.yyyymmdd;
    }

    public String getStarthhmm() {
        return this.starthhmm;
    }

    public String getEndhhmm() {
        return this.endhhmm;
    }

    public Rule getRule() {
        return this.rule;
    }
}