package com.naosim.dddwork.kintai.datasource.csv.dao;

import com.naosim.dddwork.kintai.datasource.csv.config.AppCsvProperties;
import com.naosim.dddwork.kintai.datasource.csv.entity.RegulatedBreakTimeEntities;
import com.naosim.dddwork.kintai.datasource.csv.entity.RegulatedBreakTimeEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@Component
@RequiredArgsConstructor
public class RegulatedBreakTimeCsvDao {
    private final CsvDao<RegulatedBreakTimeEntity> csvDao = new CsvDao<>();
    private final AppCsvProperties appCsvProperties;

    public RegulatedBreakTimeEntities fetchAll() throws IOException {
        List<RegulatedBreakTimeEntity> records;
        try (Reader reader = Files.newBufferedReader(Paths.get(appCsvProperties.getBreakTimeShiftPath()))) {
            records = csvDao.read(reader, RegulatedBreakTimeEntity.class);
        }
        return new RegulatedBreakTimeEntities(records);
    }
}
