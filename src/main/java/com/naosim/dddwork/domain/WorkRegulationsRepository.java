package com.naosim.dddwork.domain;

import com.naosim.dddwork.domain.workregulations.WorkRegulations;

public interface WorkRegulationsRepository {
    WorkRegulations getCurrentRegulations();
}
