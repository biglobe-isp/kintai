package jp.co.esumit.kintai.domain.kintai_info.end_time;

import lombok.RequiredArgsConstructor;
import lombok.Value;

@Value
@RequiredArgsConstructor
public class EndMinute {
    private final int value;

    public EndMinute(String endTime) {
        this.value = Integer.parseInt(endTime.substring(2));
    }

    public int getValue() {
        return this.value;
    }
}
