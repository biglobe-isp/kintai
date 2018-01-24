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
        return new KintaiOfOneDay(columns[0], columns[1], columns[2], columns[3], columns[4], columns[5]);
    }
}
