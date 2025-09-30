package org.example.domain;

import lombok.Value;

@Value
public class TotalTime {
    WorkingHours workingHours;
    Overtime overtime;
}
