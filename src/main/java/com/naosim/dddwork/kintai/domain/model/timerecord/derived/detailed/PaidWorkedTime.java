package com.naosim.dddwork.kintai.domain.model.timerecord.derived.detailed;


/**
 * 勤務時間（賃金発生分）
 */
public class PaidWorkedTime {

    final WorkedTimeAsRegular workedTimeAsRegular;
    final WorkedTimeAsOvertime workedTimeAsOvertime;


    public static PaidWorkedTime of(WorkedTimeAsRegular workedTimeAsRegular, WorkedTimeAsOvertime workedTimeAsOvertime) {
        return new PaidWorkedTime(workedTimeAsRegular, workedTimeAsOvertime);
    }

    public PaidWorkedTime(WorkedTimeAsRegular workedTimeAsRegular, WorkedTimeAsOvertime workedTimeAsOvertime) {

        this.workedTimeAsRegular = workedTimeAsRegular;
        this.workedTimeAsOvertime = workedTimeAsOvertime;
    }


    public WorkedTimeAsRegular workedTimeAsRegular() {
        return workedTimeAsRegular;
    }

    public WorkedTimeAsOvertime workedTimeAsOvertime() {
        return workedTimeAsOvertime;
    }
}
