package com.kintai;

import com.kintai.api.AttendanceRegisterApi;
import com.kintai.api.AttendanceTotalGetApi;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {
    public static void main(String[] args) {
        log.info("処理開始");
        if(args.length < 1) {
            log.error("1つ以上のパラメータが必要です。");
        } else {
            if("input".equals(args[0])) {
                AttendanceRegisterApi attendanceRegisterApi = new AttendanceRegisterApi();
                attendanceRegisterApi.register(new String[] {args[1], args[2], args[3]});
            } else if ("total".equals(args[0])) {
                AttendanceTotalGetApi attendanceTotalGetApi = new AttendanceTotalGetApi();
                attendanceTotalGetApi.getTotal();
            } else {
                log.error("選択された機能は存在しません。 " + args[0]);
            }
        }
        log.info("処理終了");
    }
}
