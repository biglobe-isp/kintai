package org.example.domain;

import lombok.Value;

@Value
public class WorkInformation {
    WorkTime workTime;
    TotalHours totalHours;
    DateToRegister dateToRegister;
    TimestampOfTheRegistration timestampOfTheRegistration;
}
