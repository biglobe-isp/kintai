package org.example.service;

import org.example.domain.WorkInformation;

import java.time.YearMonth;
import java.util.List;
import java.util.Optional;

public interface WorkInformationRepository {
    void persist(WorkInformation workInformation);
    Optional<List<Optional<WorkInformation>>> findByMonth(YearMonth month);
}
