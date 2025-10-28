package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.domain.RegisterInput;
import org.example.service.WorkInformationService;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RegisterCommand implements Command {
    private final WorkInformationService workInformationService;

    @Override
    public void execute(String[] args) {
        try {
            RegisterInput registerInput = RegisterCommandLine.toRegisterInformation(args);
            String result = workInformationService.register(registerInput) ? "success" : "fail";
            System.out.println(result);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            System.out.println("Usage: java Main input -date:yyyyMMdd [options]");
        }
    }

    @Override
    public String getCommandName() {
        return "input";
    }
}
