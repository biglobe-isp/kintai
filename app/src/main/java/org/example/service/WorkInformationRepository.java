package org.example.service;

import org.example.domain.Month;
import org.example.domain.WorkInformation;

import java.util.List;
import java.util.Optional;

public interface WorkInformationRepository {
    void persist(WorkInformation workInformation);
    Optional<List<Optional<WorkInformation>>> findByMonth(Month month);
}
