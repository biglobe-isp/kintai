package com.kintai;

import com.kintai.api.AttendanceRegisterApi;
import com.kintai.api.AttendanceTotalGetApi;
import lombok.extern.slf4j.Slf4j;

/**
 * Kintaiアプリメインクラス　※本アプリではコントローラ層の役割を担います。
 */
@Slf4j
public class Main {
    /**
     * mainメソッド。アプリで一番最初に呼び出されるメソッドです。
     * 引数に入力された値で各APIを呼び出します。
     * 引数が1つも設定されていない場合は、処理を中断します。
     * @param args アプリ実行時に入力された引数。呼び出すAPIによって引数が変わる。
     *             各APIの引数は以下の通り
     *             ・勤怠登録API({@link AttendanceRegisterApi})
     *              args[0]：APIタイトル(固定値：input)
     *              args[1]：勤怠日(yyyyMMdd)
     *              args[2]：始業時刻(HHmm)
     *              args[3]：終業時刻(HHmm)
     *              args[4]：DB登録パスワード(アプリ実行時の曜日※を入力)
     *             ・勤怠集計API({@link AttendanceTotalGetApi})
     *              args[0]：APIタイトル(固定値：input)
     *              args[1]：DB登録パスワード(アプリ実行時の曜日※を入力)
     *           ※パスワードとして入力する曜日は英語にすること 日曜日ならSunday
     */
    public static void main(String[] args) {
        log.info("処理開始");
        if(args.length < 1) {
            log.error("1つ以上のパラメータが必要です。");
        } else {
            // API呼び出し処理
            if("input".equals(args[0])) {
                AttendanceRegisterApi attendanceRegisterApi = new AttendanceRegisterApi();
                attendanceRegisterApi.register(new String[] {args[1], args[2], args[3], args[4]});
            } else if ("total".equals(args[0])) {
                AttendanceTotalGetApi attendanceTotalGetApi = new AttendanceTotalGetApi();
                attendanceTotalGetApi.getTotal(new String[] {args[1]});
            } else {
                log.error("選択された機能は存在しません。 " + args[0]);
            }
        }
        log.info("処理終了");
    }
}
