package com.naosim.dddwork.domain;


import lombok.Getter;

public class WorkTimeRegistrationApplication {
    @Getter
    private WorkDay workDay;
    @Getter
    private StartingHours startingHours;
    @Getter
    private ClosingTime closingTime;}
