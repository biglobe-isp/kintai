package com.naosim.dddwork.domain;

import lombok.Getter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Getter
public class Attendance {
    private String yyyymmdd;
    private String starthhmm;
    private String endhhmm;
    private LocalDate targetDate;
    private TimeSpan time;
    private int workMinutes, orverWorkMinutes;
    private RuleId ruleId;

    public Attendance(String yyyymmdd, String starthhmm, String endhhmm) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        this.yyyymmdd = yyyymmdd;
        this.starthhmm = starthhmm;
        this.endhhmm = endhhmm;
        this.targetDate = LocalDate.parse(yyyymmdd, formatter);
        this.time = new TimeSpan(starthhmm, endhhmm);
        this.workMinutes = this.time.getMinutes();
        this.orverWorkMinutes = 0;
        this.ruleId = null;
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
        this.ruleId = null;
    }

    public Attendance applyRule(Rule rule) {

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

        this.ruleId = rule.getRuleId();

        return this;
    }
}