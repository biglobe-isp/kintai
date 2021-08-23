package jp.co.esumit.kintai.domain.kintai_info.end_time;

import lombok.RequiredArgsConstructor;
import lombok.Value;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Value
@RequiredArgsConstructor
public class EndTime {
    private final EndHour endHr;
    private final EndMinute endMin;

    public EndTime(String endTime) {

        this.valid(endTime);

        this.endHr = new EndHour(endTime);
        this.endMin = new EndMinute(endTime);
    }

    public int getEndHrValue() {

        return this.endHr.getValue();
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

    public int getMinutes() {

        return this.getEndHrValue() * 60 + this.getEndMinValue();
    }

    private void valid(String endTime) {
        Pattern p = Pattern.compile("[0-9]{4}");
        Matcher m = p.matcher(endTime);
        if (!m.matches()) {
            throw new IllegalArgumentException("終了時刻が不正値です。");
        }
    }
}
