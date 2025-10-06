package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.domain.TotalOverTimeHours;
import org.example.domain.TotalWorkingHours;
import org.example.domain.WorkEndTime;
import org.example.domain.WorkInformation;
import org.example.domain.WorkStartTime;
import org.example.domain.WorkTime;
import org.springframework.stereotype.Service;

import java.time.YearMonth;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WorkInformationService {
    private final WorkInformationRepository workInformationRepository;

    public boolean register(WorkInformation workInformation) {
        try {
            workInformationRepository.persist(workInformation);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public WorkTime getTotalWorkTime(YearMonth month) {
        Optional<List<Optional<WorkInformation>>> workInformationList = workInformationRepository.findByMonth(month);

        return new WorkTime(
                new TotalWorkingHours(
                        workInformationList.map(list -> list.stream()
                                .mapToInt(wi -> wi.map(workInformation -> workInformation
                                        .getWorkTime()
                                        .getTotalWorkingHours()
                                        .getValue()).orElse(0))
                                .sum()).orElse(0)),
                new TotalOverTimeHours(
                        workInformationList.map(list -> list.stream()
                                .mapToInt(wi -> wi.map(workInformation -> workInformation
                                        .getWorkTime()
                                        .getTotalOverTimeHours()
                                        .getValue()).orElse(0))
                                .sum()).orElse(0)),
                WorkStartTime.of("00_00"),
                WorkEndTime.of("00_00")
        );
    }
}