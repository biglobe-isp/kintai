package com.naosim.dddwork.kintai.domain.model.timerecord.derived;

import com.naosim.dddwork.kintai.domain.model.timerecord.derived.detailed.PaidWorkedTime;

import java.io.PrintStream;


/**
 * 月次トータル勤務時間
 */
public class MonthlyTotalWorkedTime {

//TODO: 年月が必要かな？

//TODO: AmountOfMinutes は制限ありなのでサマリには使えない。他の型を用意すべきか？
    final int totalPaidWorkMinutes;
    final int totalOvertimeMinutes;


    /* 生成 */

    public MonthlyTotalWorkedTime(int totalPaidWorkMinutes, int totalOvertimeMinutes) {

        this.totalPaidWorkMinutes = totalPaidWorkMinutes;
        this.totalOvertimeMinutes = totalOvertimeMinutes;
    }

    public static MonthlyTotalWorkedTime zero() {
        return new MonthlyTotalWorkedTime(0, 0);
    }


    public MonthlyTotalWorkedTime added(PaidWorkedTime paidWorkedTime) {

        final int totalPaidWorkMinutes = this.totalPaidWorkMinutes + paidWorkedTime.totalMinutes().rawValue();
        final int overtimeMinutes = this.totalOvertimeMinutes + paidWorkedTime.overtimeMinutes().rawValue();

        return new MonthlyTotalWorkedTime(totalPaidWorkMinutes, overtimeMinutes);
    }

    public MonthlyTotalWorkedTime added(MonthlyTotalWorkedTime other) {

        final int totalPaidWorkMinutes = this.totalPaidWorkMinutes + other.totalPaidWorkMinutes;
        final int overtimeMinutes = this.totalOvertimeMinutes + other.totalOvertimeMinutes;

        return new MonthlyTotalWorkedTime(totalPaidWorkMinutes, overtimeMinutes);
    }


    /* 表示 */

    //TODO: これの呼び出し元に移動する
    /**
     * 指定されたプリントストリームに表示する．
     * <pre>
     *     QUESTION: この方式がいいのか、それとも getterを生やして OutputPortの責務にする方がいいのか？ ヘキサゴナルアーキテクチャに従えばアダプター側かな。
     * </pre>
     *
     * @param printStream プリントストリーム
     */
    public void showOn(PrintStream printStream) {

        printStream.println("勤務時間: " + totalPaidWorkMinutes / 60 + "時間" + totalPaidWorkMinutes % 60 + "分");
        printStream.println("残業時間: " + totalOvertimeMinutes / 60 + "時間" + totalOvertimeMinutes % 60 + "分");
    }
}
