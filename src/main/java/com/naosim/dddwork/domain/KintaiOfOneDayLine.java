package com.naosim.dddwork.domain;


import com.naosim.dddwork.domain.time.Minute;
import com.naosim.dddwork.domain.time.Now;
import com.naosim.dddwork.domain.time.work.WorkDate;
import com.naosim.dddwork.domain.time.work.WorkEndTime;
import com.naosim.dddwork.domain.time.work.WorkStartTime;
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
                new WorkDate(columns[0]),
                new WorkStartTime(columns[1]),
                new WorkEndTime(columns[2]),
                new Minute(Integer.parseInt(columns[3])),
                new Minute(Integer.parseInt(columns[4])),
                new Now(columns[5])
        );
    }
}
