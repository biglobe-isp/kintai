package com.naosim.dddwork.domain.kintai;

import com.naosim.dddwork.domain.kintai.time.Now;
import com.naosim.dddwork.domain.kintai.time.work.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
public class KintaiOfOneDay {

    @Getter
    private final WorkDate workDate;

    @Getter
    private final WorkStartTime workStartTime;

    @Getter
    private final WorkEndTime workEndTime;

    @Getter
    private final WorkMinutes workMinutes;

    @Getter
    private final OverWorkMinutes overWorkMinutes;

    @Getter
    private final Now registerDateTime;

    public KintaiOfOneDay(String line) {
        String[] columns = line.split(",");
        this.workDate = new WorkDate(columns[0]);
        this.workStartTime = new WorkStartTime(columns[1]);
        this.workEndTime = new WorkEndTime(columns[2]);
        this.workMinutes = new WorkMinutes(Integer.parseInt(columns[3]));
        this.overWorkMinutes = new OverWorkMinutes(Integer.parseInt(columns[4]));
        this.registerDateTime = new Now(columns[5]);
    }
}
