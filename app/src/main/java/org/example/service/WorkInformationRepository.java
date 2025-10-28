package org.example.service;

import org.example.domain.TotalHours;
import org.example.domain.WorkInformation;
import org.example.domain.WorkYearMonth;

import java.util.List;
import java.util.Optional;

public interface WorkInformationRepository {
    void persist(WorkInformation workInformation);
    Optional<List<Optional<TotalHours>>> findByMonth(WorkYearMonth workYearMonth);
}
