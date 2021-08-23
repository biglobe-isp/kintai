package jp.co.esumit.kintai.domain.kintai_info.end_time;

import lombok.RequiredArgsConstructor;
import lombok.Value;

@Value
@RequiredArgsConstructor
public class EndHour {
    private final int value;

    public EndHour(String endTime) {
        this.value = Integer.parseInt(endTime.substring(0, 2));
    }

    public int getValue() {
        return this.value;
    }
}
