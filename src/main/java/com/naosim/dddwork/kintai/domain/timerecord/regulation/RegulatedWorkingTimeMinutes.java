package com.naosim.dddwork.kintai.domain.timerecord.regulation;

import com.naosim.dddwork.kintai.domain.timerecord.TimeLength;
import lombok.NonNull;
import lombok.Value;

@Value
public class RegulatedWorkingTimeMinutes {
    @NonNull
    TimeLength length;
}
