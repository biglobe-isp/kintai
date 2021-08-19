package jp.co.esumit.kintai.domain.kintai_info.start_time;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StartTime {
    private final StartHour startHr;
    private final StartMinute startMin;

    public StartTime(String startTime) {

        this.valid(startTime);

        this.startHr = new StartHour(startTime);
        this.startMin = new StartMinute(startTime);
    }

    public StartHour getStartHr() {
        return this.startHr;
    }

    public int getStartHrValue() {

        return this.startHr.getValue();
    }

    public StartMinute getStartMin() {
        return this.startMin;
    }

    public int getStartMinValue() {

        return this.startMin.getValue();
    }

    public String toString() {
        return String.format(
                "%02d%02d",
                this.getStartHrValue(), this.getStartMinValue()
        );
    }

    private void valid(String startTime) {
        Pattern p = Pattern.compile("[0-9]{4}");
        Matcher m = p.matcher(startTime);
        if (!m.matches()) {
            throw new IllegalArgumentException("開始時刻が不正値です。");
        }
    }
}
