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
        try {
            String yearMonth = GetTotalTimeCommandLine.toYearMonth(args);
            TotalHours totalHours = workInformationService.getTotalWorkTime(yearMonth);
            System.out.println(GetTotalTimeCommandLine.getTotalTime(totalHours));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Usage: java Main total -yearMonth:<yyyy_MM>");
        }
    }

    @Override
    public String getCommandName() {
        return "total";
    }
}
