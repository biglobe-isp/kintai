package jp.co.esumit.kintai.domain;

import jp.co.esumit.kintai.domain.field.RegisteredTime;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CalcRegisteredTime {
    public RegisteredTime getRegisteredTime(){
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss" );
        String now = format.format(date);
        return new RegisteredTime(now);
    }
}