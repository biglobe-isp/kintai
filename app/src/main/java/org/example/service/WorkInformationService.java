package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.domain.Month;
import org.example.domain.Overtime;
import org.example.domain.Time;
import org.example.domain.TotalTime;
import org.example.domain.WorkInformation;
import org.example.domain.WorkingHours;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class WorkInformationService {
    private final WorkInformationRepository workInformationRepository;

    void register(WorkInformation workInformation) {
        workInformationRepository.persist(workInformation);
    }
//    TotalTime getTotalTime(Month month){
//        return new TotalTime(
//                new WorkingHours(
//                        workInformationRepository.findByMonth(month).orElse(List.of()).stream()
//                                .filter(Optional::isPresent)
//                                .map(Optional::get)
//                                .map(workInformation -> workInformation.getWorkTime()
//                ),
//                new Overtime(
//                        workInformationRepository.findByMonth(month).orElse(List.of()).stream()
//                                .filter(Optional::isPresent)
//                                .map(Optional::get)
//                                .map(WorkInformation::getOvertime)
//                                .map(overtime -> overtime.getHour() * 60 + overtime.getMinute())
//                                .reduce(0, Integer::sum)
//                )
//        );
//    };
}
//Optional<List<Optional<WorkInformation>>>