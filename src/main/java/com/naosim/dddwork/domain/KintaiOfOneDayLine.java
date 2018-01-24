package com.naosim.dddwork.domain;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
public class KintaiOfOneDayLine {

    @Getter
    private final String value;

    public KintaiOfOneDay getKintaiOfOneDay() {
        String[] columns = this.value.split(",");
        return new KintaiOfOneDay(
                new DateString(columns[0]),
                new TimeString(columns[1]),
                new TimeString(columns[2]),
                new Minute(Integer.parseInt(columns[3])),
                new Minute(Integer.parseInt(columns[4])),
                new NowString(columns[5])
        );
    }
}
