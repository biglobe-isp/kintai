package com.naosim.dddwork.kintai;

import com.naosim.dddwork.kintai.api.AttendanceController;
import com.naosim.dddwork.kintai.api.Usage;

public class App {

    public static void main(String[] args) {

        try {
            new AttendanceController(args).execute();
        }
        catch (Exception e) {
            e.printStackTrace();
            Usage.print();
        }
    }
}