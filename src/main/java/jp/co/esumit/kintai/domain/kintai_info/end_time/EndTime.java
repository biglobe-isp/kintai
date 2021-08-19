package jp.co.esumit.kintai.domain.kintai_info.end_time;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EndTime {
    public final EndHour endHr;
    public final EndMinute endMin;

    public EndTime(String endTime) {

        this.valid(endTime);

        this.endHr = new EndHour(endTime);
        this.endMin = new EndMinute(endTime);
    }

    public EndHour getEndHr() {
        return this.endHr;
    }

    public int getEndHrValue() {
        return this.endHr.getValue();
    }

    public EndMinute getEndMin() {
        return this.endMin;
    }

    public int getEndMinValue() {

        return this.endMin.getValue();
    }

    public String toString() {
        return String.format(
                "%02d%02d",
                this.getEndHrValue(), this.getEndMinValue()
        );
    }

    private void valid(String endTime) {
        Pattern p = Pattern.compile("[0-9]{4}");
        Matcher m = p.matcher(endTime);
        if (!m.matches()) {
            throw new IllegalArgumentException("終了時刻が不正値です。");
        }
    }
}
