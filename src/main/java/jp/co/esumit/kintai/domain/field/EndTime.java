package jp.co.esumit.kintai.domain.field;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EndTime {
    public final EndHour endHr;
    public final EndMinute endMin;

    public EndTime(String endTime){

        this.valid(endTime);

        this.endHr = new EndHour(endTime);
        this.endMin = new EndMinute(endTime);
    }

    public EndHour getEndHr(){
        return this.endHr;
    }

    public EndMinute getEndMin(){
        return this.endMin;
    }

    private void valid(String endTime) {
        Pattern p = Pattern.compile("[0-9]{4}");
        Matcher m = p.matcher(endTime);
        if (!m.matches()) {
            throw new IllegalArgumentException("終了時刻が不正値です。");
        }
    }
}
