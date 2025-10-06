package org.example.controller;

public class FormatParser {
    public static String parseData(String data) {
        final String[] split = data.split(":");
        if(split[1] == null || split[1].isBlank()) {
            throw new IllegalArgumentException("Value for " + split[0] + " is missing or empty.");
        }
        return split[1];
    }
}
