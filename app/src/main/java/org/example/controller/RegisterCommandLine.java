package org.example.controller;

import lombok.Value;
import org.example.domain.DateToRegister;
import org.example.domain.TimestampOfTheRegistration;
import org.example.domain.WorkEndTime;
import org.example.domain.WorkInformation;
import org.example.domain.WorkStartTime;
import org.example.domain.WorkTime;

import java.time.LocalDateTime;

import static org.example.controller.FormatParser.parseData;

public class RegisterCommandLine {
    public static WorkInformation of(String[] args) {
        if (args.length != 3) {
            throw new IllegalArgumentException("Invalid number of arguments. Expected 3 arguments: name, age, position.");
        }

        String inputDate = args[0];
        String inputStartTime = args[1];
        String inputEndTime = args[2];

        String dateToRegister = parseData(inputDate);
        String startTimeToRegister = parseData(inputStartTime);
        String endTimeToRegister = parseData(inputEndTime);
        LocalDateTime timeStampOfTheRegister = LocalDateTime.now();

        if (dateToRegister == null || startTimeToRegister == null || endTimeToRegister == null) {
            throw new IllegalArgumentException(
                    "Invalid argument format. Expected format: key-value (e.g., date-2023/10/01).");
        }


        return new WorkInformation(
                        WorkTime.of(
                                WorkStartTime.of(
                                        startTimeToRegister
                                ),
                                WorkEndTime.of(
                                        endTimeToRegister
                                )
                        ),
                        DateToRegister.of(dateToRegister),
                        new TimestampOfTheRegistration(timeStampOfTheRegister)
                );
    }
}
