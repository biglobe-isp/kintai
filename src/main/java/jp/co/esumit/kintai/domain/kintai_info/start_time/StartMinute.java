package jp.co.esumit.kintai.domain.kintai_info.start_time;

import lombok.RequiredArgsConstructor;
import lombok.Value;

@Value
@RequiredArgsConstructor
public class StartMinute {
    int value;

    public StartMinute(String startTime) {
        this.value = Integer.parseInt(startTime.substring(2));
    }

    public int getValue() {
        return this.value;
    }
}
