package com.naosim.dddwork.api;

import com.google.common.base.Strings;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        com.naosim.dddwork.Main oldMain = new com.naosim.dddwork.Main();
        oldMain.main(args);
        System.out.println("finished kintai registration!");
    }



// TODO:引数のチェックで利用する
//    public TimeUnit(String inputTime) {
//        if (!isTimeValue(inputTime)) {
//            throw new RuntimeException("Invalid time.");
//        }
//        this.hour = Integer.parseInt(inputTime.substring(0, 2));
//        this.minutes = Integer.parseInt(inputTime.substring(2));
//    }
//
//    private boolean isTimeValue(String inputTime) {
//        if (Strings.isNullOrEmpty(inputTime))
//            return false;
//        Pattern p = Pattern.compile("^([0-1][0-9]|[2][0-9])[0-5][0-9]$");
//        Matcher m = p.matcher(inputTime);
//        if ( !m.find() )
//            return false;
//        return true;
//    }
}
