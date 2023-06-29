package jp.co.biglobe.isp.kintai.domain.time_range;

import lombok.Value;

@Value
public class TimeSpanMinutes {
    int value;

    TimeSpanMinutes(long _value) {
        if(_value < 0 ) throw new RuntimeException();
        value = (int) _value;
    }
}
