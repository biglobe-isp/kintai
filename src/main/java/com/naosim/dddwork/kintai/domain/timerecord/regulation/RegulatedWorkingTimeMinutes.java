package com.naosim.dddwork.kintai.domain.timerecord.regulation;

import com.naosim.dddwork.kintai.domain.timerecord.TimeLength;
import lombok.Value;

@Value
public class RegulatedWorkingTimeMinutes {

    TimeLength length;
}
