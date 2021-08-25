package com.naosim.dddwork.kintai.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

@RequiredArgsConstructor
@Component
public class AttendanceServiceHandler {

    private final AttendanceController attendanceController;

    public void call(String ...args) throws Exception {
        // TODO: リファクタ
        if (args.length < 1) {
            throw new IllegalArgumentException("引数が足りません");
        }
        String feature = args[0];
        String[] inputParams = Stream.of(args).skip(1).toArray(String[]::new);

        if ("input".equals(feature)) {
            if (inputParams.length != 3) {
                throw new IllegalArgumentException("引数不正です。 引数：" + inputParams.length);
            }
            attendanceController.record(inputParams);
        } else if ("total".equals(feature)) {
            attendanceController.aggregateMonthly(inputParams);
        } else {
            throw new IllegalArgumentException("第一引数が不正です。そのような機能はありません。");
        }
    }

}
