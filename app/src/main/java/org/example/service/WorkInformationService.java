package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.domain.RegisterInput;
import org.example.domain.TotalHours;
import org.example.domain.TotalOverTimeHours;
import org.example.domain.TotalWorkingHours;
import org.example.domain.WorkInformation;
import org.springframework.stereotype.Service;

import java.time.YearMonth;
import java.util.List;
import java.util.Optional;

import static org.example.utils.DateTimeFormatters.YEAR_MONTH;

@Service
@RequiredArgsConstructor
public class WorkInformationService {
    private final WorkInformationRepository workInformationRepository;

    public boolean register(RegisterInput registerInput) {
        try {
            WorkInformation workInformation = registerInput.toWorkInformation();
            workInformationRepository.persist(workInformation);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public TotalHours getTotalWorkTime(String month) {
        YearMonth yearMonth = YearMonth.parse(month, YEAR_MONTH);
        Optional<List<Optional<TotalHours>>> totalHoursList = workInformationRepository.findByMonth(yearMonth);

        return new TotalHours(
                new TotalWorkingHours(
                        totalHoursList.map(list -> list.stream()
                                .mapToInt(th -> th.map(totalHours -> totalHours
                                        .getTotalWorkingHours()
                                        .getValue()).orElse(0))
                                .sum()).orElse(0)),
                new TotalOverTimeHours(
                        totalHoursList.map(list -> list.stream()
                                .mapToInt(th -> th.map(totalHours -> totalHours
                                        .getTotalOverTimeHours()
                                        .getValue()).orElse(0))
                                .sum()).orElse(0))
        );
    }
}