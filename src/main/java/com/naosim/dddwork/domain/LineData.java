package com.naosim.dddwork.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
public class LineData {

    @Getter
    private final String workDate;

    @Getter
    private final String startTIme;

    @Getter
    private final String endTime;

    @Getter
    private final String workMinutes;

    @Getter
    private final String overWorkMinutes;

    @Getter
    private final String now;

    public String getLineString() {
        return String.format("%s,%s,%s,%s,%s,%s\n",
                this.workDate, this.startTIme, this.endTime,
                this.workMinutes, this.overWorkMinutes, this.now
        );
    }
}
