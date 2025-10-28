package org.example.controller;

import java.util.List;
import java.util.Objects;

public class FormatParser {
    public static String parseDataOfCoron(String data) {
        final String[] split = data.split(":");
        if(split[1] == null || split[1].isBlank()) {
            throw new IllegalArgumentException("Value for " + split[0] + " is missing or empty.");
        }
        return split[1];
    }

    public static List<String> prepareWorkTimeArgs(String[] args) {
        String regularStartTime = "09_00";
        String regularEndTime = "18_00";

        if (args.length == 1 && Objects.equals(args[0], "v")) {
            return List.of(regularStartTime, regularEndTime);
        }

        if (args.length != 2 ) {
            throw new IllegalArgumentException("Invalid number of arguments. Expected 2 arguments: -start:HH_mm -end:HH_mm");
        }

        if(Objects.equals(args[0], "am")) {
            return List.of(regularStartTime, parseDataOfCoron(args[1]));
        }

        if(Objects.equals(args[1], "pm")) {
            return List.of(parseDataOfCoron(args[0]), regularEndTime);
        }

        return List.of(parseDataOfCoron(args[0]), parseDataOfCoron(args[1]));
    }
}
