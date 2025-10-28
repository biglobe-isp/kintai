package org.example.controller;

import org.example.domain.RegisterInput;
import java.util.Arrays;
import java.util.List;

import static org.example.controller.FormatParser.parseDataOfCoron;
import static org.example.controller.FormatParser.prepareWorkTimeArgs;

public class RegisterCommandLine {
    public static RegisterInput toRegisterInformation(String[] args) {
        String inputDate = args[0];

        String[] inputTimes = Arrays.copyOfRange(args, 1, args.length);
        List<String> prepareworkTimeList = prepareWorkTimeArgs(inputTimes);

        String startTimeToRegisterString = prepareworkTimeList.get(0);
        String endTimeToRegisterString = prepareworkTimeList.get(1);
        String dateToRegisterString = parseDataOfCoron(inputDate);

        if (dateToRegisterString == null || startTimeToRegisterString == null || endTimeToRegisterString == null) {
            throw new IllegalArgumentException(
                    "Invalid argument format. Expected format: key:value (e.g., date:20231001).");
        }

        return new RegisterInput(
                startTimeToRegisterString,endTimeToRegisterString, dateToRegisterString
        );
    }
}
