package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.domain.TotalHours;
import org.example.service.WorkInformationService;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetTotalTimeCommand implements Command {
    private final WorkInformationService workInformationService;

    @Override
    public void execute(String[] args) {
        String yearMonth = GetTotalTimeCommandLine.toYearMonth(args);
        TotalHours totalHours = workInformationService.getTotalWorkTime(yearMonth);
        System.out.println(GetTotalTimeCommandLine.getTotalTime(totalHours));
    }

    @Override
    public String getCommandName() {
        return "total";
    }
}
