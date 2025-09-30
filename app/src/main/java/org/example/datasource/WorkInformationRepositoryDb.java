package org.example.datasource;

import lombok.RequiredArgsConstructor;
import org.example.domain.Month;
import org.example.domain.WorkInformation;
import org.example.service.WorkInformationRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class WorkInformationRepositoryDb implements WorkInformationRepository {
    private final WorkInformationCSVMapper workInformationCSVMapper;
    @Override
    public void persist(WorkInformation workInformation) {
        workInformationCSVMapper.insert(workInformation);
    }

    @Override
    public Optional<List<Optional<WorkInformation>>> findByMonth(Month month) {
        return workInformationCSVMapper.findByMonth(month);
    }
}
