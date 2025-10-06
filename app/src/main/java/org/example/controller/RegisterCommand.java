package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.domain.WorkInformation;
import org.example.service.WorkInformationService;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RegisterCommand implements Command {
    private final WorkInformationService workInformationService;

    @Override
    public void execute(String[] args) {
        try {
            WorkInformation workInformation = RegisterCommandLine.of(args);
            String result = workInformationService.register(workInformation) ? "success" : "fail";
            System.out.println(result);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            System.out.println("Usage: java Main input -date:yyyyMMdd -start:HH_mm -end:HH_mm");
        }
    }

    @Override
    public String getCommandName() {
        return "input";
    }
}
