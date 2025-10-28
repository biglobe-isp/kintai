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
        RegisterInput registerInput = RegisterCommandLine.toRegisterInformation(args);
        String result = workInformationService.register(registerInput) ? "success" : "fail";
        System.out.println(result);
    }

    @Override
    public String getCommandName() {
        return "input";
    }
}
