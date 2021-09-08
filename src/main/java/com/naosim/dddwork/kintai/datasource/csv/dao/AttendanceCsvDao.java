package com.naosim.dddwork.kintai.datasource.csv.dao;

import com.naosim.dddwork.kintai.datasource.csv.config.AppCsvProperties;
import com.naosim.dddwork.kintai.datasource.csv.entity.AttendanceRecordEntities;
import com.naosim.dddwork.kintai.datasource.csv.entity.AttendanceRecordEntity;
import com.naosim.dddwork.kintai.domain.aggregation.AggregationMonth;
import com.opencsv.exceptions.CsvException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class AttendanceCsvDao {
    private final CsvDao<AttendanceRecordEntity> csvDao = new CsvDao<>();
    private final AppCsvProperties appCsvProperties;

    public AttendanceRecordEntities fetchMonthly(AggregationMonth aggregationMonth) throws IOException {
        Map<LocalDate, AttendanceRecordEntity> recordMap;
        try (Reader reader = Files.newBufferedReader(Paths.get(appCsvProperties.getAttendanceDataPath()))) {
            recordMap = csvDao
                    .read(reader, AttendanceRecordEntity.class)
                    .stream()
                    .filter(rec -> aggregationMonth.equalsYearMonth(rec.getAttendanceDate()))
                    .collect(Collectors.toMap(
                            AttendanceRecordEntity::getAttendanceDate,
                            rec -> rec,
                            (oldVal, newVal) -> newVal,
                            HashMap::new
                    ));
        }
        return new AttendanceRecordEntities(new ArrayList<>(recordMap.values()));
    }

    public void register(AttendanceRecordEntities records) throws IOException, CsvException {
        try (Writer writer = Files.newBufferedWriter(
                Paths.get(appCsvProperties.getAttendanceDataPath()),
                StandardOpenOption.APPEND
        )) {
            csvDao.writeAll(writer, records.getRecords());
        }
    }
}
