package com.naosim.dddwork.datasource;

import org.springframework.stereotype.Component;

@Component
public class CsvSetting {

    private static final String filepath = "data.csv";

    String getFilepath() {
        return filepath;
    }
}
