package com.naosim.dddwork.domain.attendance;

import com.naosim.dddwork.domain.TimeUnit;
import lombok.Value;

@Value(staticConstructor="of")
public class WorkingHours {
    TimeUnit timeUnit;
}
