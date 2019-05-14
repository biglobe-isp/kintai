package com.naosim.dddwork.kintai.api;

import com.naosim.dddwork.kintai.shared.EasyPrinter;


/**
 * 使い方
 */
public class Usage implements EasyPrinter {

    public static void print() {
        new Usage()._print();
    }

    private void _print() {

        println();
        println("--- 使い方 ---------------------------------------------------------");
        println();
        println("※ ()内はフォーマットです。");
        println();
        println("◆指定日の勤怠登録");
        println("   input 勤怠日付(yyyyMMdd) 出勤時刻(HHmm) 退勤時刻(HHmm)");
        println("   例)");
        println("       input 20190107 0900 1800");
        println("       input 20190107 0900 2400");
        println("       input 20190107 0900 2530");
        println("       input 20190107 0900 3300  // ← 退勤時刻は、翌日9:00が入力上限");
        println("   修正は同じ勤怠日付で再度リクエストすればよい。");
        println();
        println("◆指定月の勤務時間合計表示");
        println("   total 勤怠月(yyyyMM)");
        println("   例)");
        println("       total 201901");
        println("-------------------------------------------------------------------");
        println();


    }

}
