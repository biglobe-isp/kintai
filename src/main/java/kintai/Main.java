package kintai;

import kintai.domain.WorkEnd;
import kintai.domain.InputAttendance;
import kintai.domain.WorkStart;
import kintai.domain.WorkDay;
import kintai.service.InputAttendanceService;

import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {

        if (args.length < 1) {
            throw new RuntimeException("引数が足りません");
        }
        if (!args[1].startsWith("-date:")) {
            throw new RuntimeException("-date:から入力を開始して下さい");
        }
        if (!args[1].startsWith("-start:")) {
            throw new RuntimeException("-start:から入力を開始して下さい");
        }
        if (!args[2].startsWith("-end:")) {
            throw new RuntimeException("-end:から入力を開始して下さい");
        }
        String methodType = args[0];
        String date = args[0].substring(0, 6);
        String start = args[1].substring(0, 7);
        String  end = args[2].substring(0, 5);
        String now = LocalDateTime.now().toString();
        
        InputAttendance inputAttendance = new InputAttendance(WorkDay.parseyyyyMMdd(date), WorkStart.parseHHmm(start), WorkEnd.parseHHmm(end));


    }
}
