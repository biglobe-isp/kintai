package com.naosim.dddwork.kintai.domain.timerecord.regulation;

import lombok.NonNull;
import lombok.Value;

import java.util.List;

@Value
public class RegulatedBreakTimeShift {
    @NonNull
    List<RegulatedBreakTimeInterval> intervals;
}
