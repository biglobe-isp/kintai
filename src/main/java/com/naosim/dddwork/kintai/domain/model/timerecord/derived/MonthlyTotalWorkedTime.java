package com.naosim.dddwork.kintai.domain.model.timerecord.derived;

import java.io.PrintStream;


/**
 * 月次トータル勤務時間
 */
public class MonthlyTotalWorkedTime {

//TODO: 年月が必要だ

    final int totalWorkMinutes;
    final int totalOverWorkMinutes;


    /* 生成 */

    public MonthlyTotalWorkedTime(int totalWorkMinutes, int totalOverWorkMinutes) {
        this.totalWorkMinutes = totalWorkMinutes;
        this.totalOverWorkMinutes = totalOverWorkMinutes;
    }


    /**
     * 指定されたプリントストリームに表示する．
     * <pre>
     *     QUESTION: この方式がいいのか、それとも getterを生やして OutputPortの責務にする方がいいのか？ ヘキサゴナルアーキテクチャに従えばアダプター側かな。
     * </pre>
     *
     * @param printStream プリントストリーム
     */
    public void showOn(PrintStream printStream) {

        printStream.println("勤務時間: " + totalWorkMinutes / 60 + "時間" + totalWorkMinutes % 60 + "分");
        printStream.println("残業時間: " + totalOverWorkMinutes / 60 + "時間" + totalOverWorkMinutes % 60 + "分");
    }
}
