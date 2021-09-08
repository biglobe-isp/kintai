package com.naosim.dddwork.kintai.datasource.csv.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties("app.csv")
public class AppCsvProperties {
    private String attendanceDataPath;
    private String breakTimeShiftPath;
    private String workingTimeMinutesPath;
}
