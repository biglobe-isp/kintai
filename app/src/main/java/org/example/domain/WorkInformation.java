package org.example.domain;

import lombok.Value;

@Value
public class WorkInformation {
    WorkTime workTime;
    TargetDate targetDate;
    CreateTimestamp createTimestamp;
}
