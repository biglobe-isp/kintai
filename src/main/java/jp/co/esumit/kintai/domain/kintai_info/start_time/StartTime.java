package jp.co.esumit.kintai.domain.kintai_info.start_time;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.Value;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Value
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class StartTime {
    StartHour startHr;
    StartMinute startMin;

    public StartTime(String startTime) {

        this.valid(startTime);

        this.startHr = new StartHour(startTime);
        this.startMin = new StartMinute(startTime);
    }

    public int getStartHrValue() {

        return this.startHr.getValue();
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

    public int getMinutes() {
        return this.getStartHrValue() * 60 + this.getStartMinValue();
    }

    private void valid(String startTime) {
        Pattern p = Pattern.compile("[0-9]{4}");
        Matcher m = p.matcher(startTime);
        if (!m.matches()) {
            throw new IllegalArgumentException("開始時刻が不正値です。");
        }
    }
}
