package kintai.domain;

import lombok.Value;

@Value
public class OverWorkMinutes {
    int OverWorkMinutes ;

    public static int calcOverWorkMinutes(int workMinutes) {
        int overWorkM = workMinutes - 8 * 60;
        return overWorkM;
    }
}
