package com.naosim.dddwork.kintai.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

@RequiredArgsConstructor
@Component
public class AttendanceHandler {

    private final AttendanceController attendanceController;

    public void call(String ...args) {
        if (args.length < 1) {
            throw new IllegalArgumentException("引数が足りません");
        }
        String feature = args[0];
        String[] inputParams = Stream.of(args).skip(1).toArray(String[]::new);

        switch (feature) {
            case "input":
                if (inputParams.length != 3) {
                    throw new IllegalArgumentException("引数不正です。 引数：" + inputParams.length);
                }
                attendanceController.record(inputParams);
                return;
            case "total":
                if (inputParams.length != 1) {
                    throw new IllegalArgumentException("引数不正です。 引数：" + inputParams.length);
                }
                attendanceController.aggregateMonthly(inputParams);
                return;
            default:
                throw new IllegalArgumentException("第一引数が不正です。そのような機能はありません。");
        }
    }

}
