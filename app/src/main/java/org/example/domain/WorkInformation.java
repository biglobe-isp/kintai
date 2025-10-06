package org.example.domain;

import lombok.Value;

@Value
public class WorkInformation {
    WorkTime workTime;
    DateToRegister dateToRegister;
    TimestampOfTheRegistration timestampOfTheRegistration;
}
