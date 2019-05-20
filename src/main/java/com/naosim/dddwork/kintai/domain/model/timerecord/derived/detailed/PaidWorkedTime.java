package com.naosim.dddwork.kintai.domain.model.timerecord.derived.detailed;


/**
 * 労働時間（賃金発生分）
 */
public class PaidWorkedTime {

    /** 賃金発生労働時間（通常時間） */
    final WorkedTimeAsRegular workedTimeAsRegular;
    /** 賃金発生労働時間（残業時間） */
    final WorkedTimeAsOvertime workedTimeAsOvertime;


    /* 生成 */

    /**
     * 実質労働時間とサービス残業時間で構築する．
     *
     * @param actualWorkedTime 実質労働時間
     * @param actualUnpaidWorkedTime サービス残業時間
     */
    private PaidWorkedTime(ActualWorkedTime actualWorkedTime, ActualUnpaidWorkedTime actualUnpaidWorkedTime) {

        final ActualPaidWorkedTime actualPaidWorkedTime = ActualPaidWorkedTime.of(actualWorkedTime, actualUnpaidWorkedTime);

        this.workedTimeAsRegular = WorkedTimeAsRegular.of(actualPaidWorkedTime);
        this.workedTimeAsOvertime = WorkedTimeAsOvertime.of(actualPaidWorkedTime);
    }

    public static PaidWorkedTime of(ActualWorkedTime actualWorkedTime, ActualUnpaidWorkedTime actualUnpaidWorkedTime) {
        return new PaidWorkedTime(actualWorkedTime, actualUnpaidWorkedTime);
    }


    /* 値 */

    public WorkedTimeAsRegular workedTimeAsRegular() {
        return workedTimeAsRegular;
    }

    public WorkedTimeAsOvertime workedTimeAsOvertime() {
        return workedTimeAsOvertime;
    }

    public int storedValue() {
        return workedTimeAsRegular.minutes.plus(workedTimeAsOvertime.minutes).rawValue();
    }
}
