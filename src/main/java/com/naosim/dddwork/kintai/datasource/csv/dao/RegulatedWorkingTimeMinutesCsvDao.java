package com.naosim.dddwork.kintai.datasource.csv.dao;

import com.naosim.dddwork.kintai.datasource.csv.config.AppCsvProperties;
import com.naosim.dddwork.kintai.datasource.csv.entity.RegulatedWorkingTimeMinutesEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

@Component
@RequiredArgsConstructor
public class RegulatedWorkingTimeMinutesCsvDao {
    private final CsvDao<RegulatedWorkingTimeMinutesEntity> csvDao = new CsvDao<>();
    private final AppCsvProperties appCsvProperties;

    public RegulatedWorkingTimeMinutesEntity fetch() throws IOException {
        RegulatedWorkingTimeMinutesEntity record;
        try (Reader reader = Files.newBufferedReader(Paths.get(appCsvProperties.getWorkingTimeMinutesPath()))) {
            record = csvDao.read(reader, RegulatedWorkingTimeMinutesEntity.class)
                    .stream()
                    .findFirst()
                    .orElseThrow(RuntimeException::new);
        }
        return record;
    }
}
