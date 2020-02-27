package com.naosim.dddwork.domain;

import com.naosim.dddwork.domain.workregulations.WorkRegulations;

public interface IOverTimeHoursCalculator {
    TimeUnit calcOverTimeHours(
            TimeUnit workingHours, WorkRegulations workRegulations);
}
