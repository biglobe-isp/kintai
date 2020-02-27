package com.naosim.dddwork.domain.attendance;

import com.naosim.dddwork.domain.TimePoint;
import lombok.Value;

@Value(staticConstructor="of")
public class StartTime {
    TimePoint timePoint;
}
