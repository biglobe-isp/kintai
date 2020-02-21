package com.naosim.dddwork.domain;

import lombok.Value;

@Value(staticConstructor="of")
public class TimeUnit {
    int hour;
    int minutes;
}
