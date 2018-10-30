package jp.co.biglobe.kintai.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class EndWorkTime {

    @Getter
    private final String time;

    public int getHoursAsInt(){
        return Integer.valueOf(time.substring(0,2));
    }

    public int getMinutesAsInt(){
        return Integer.valueOf(time.substring(2,4));
    }
}
