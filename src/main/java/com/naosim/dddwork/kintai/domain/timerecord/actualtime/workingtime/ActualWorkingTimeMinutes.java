package com.naosim.dddwork.kintai.domain.timerecord.actualtime.workingtime;

import com.naosim.dddwork.kintai.domain.timerecord.TimeLength;
import lombok.Value;

@Value
public class ActualWorkingTimeMinutes {

    TimeLength length;

    public int intValue() {
        return (int)this.length.getLength();
    }

}
