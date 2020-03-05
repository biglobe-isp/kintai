package com.naosim.dddwork.domain.service;

import com.naosim.dddwork.domain.attendance.OverTimeHours;
import com.naosim.dddwork.domain.attendance.WorkingHours;
import com.naosim.dddwork.domain.workregulations.WorkRegulations;

public interface OverTimeHoursCalculable {
    OverTimeHours calcOverTimeHours(
            WorkingHours workingHours, WorkRegulations workRegulations);
}
