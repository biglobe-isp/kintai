package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.domain.WorkTime;
import org.example.service.WorkInformationService;
import org.springframework.stereotype.Component;

import java.time.YearMonth;

@Component
@RequiredArgsConstructor
public class GetTotalTimeCommand implements Command {
    private final WorkInformationService workInformationService;

    @Override
    public void execute(String[] args) {
        try {
            YearMonth yearMonth = GetTotalTimeCommandLine.of(args);
            WorkTime workTime = workInformationService.getTotalWorkTime(yearMonth);
            System.out.println(GetTotalTimeCommandLine.getTotalTime(workTime));
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
