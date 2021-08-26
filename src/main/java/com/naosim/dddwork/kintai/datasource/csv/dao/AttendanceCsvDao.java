package com.naosim.dddwork.kintai.datasource.csv.dao;

import com.naosim.dddwork.kintai.datasource.csv.entity.AttendanceRecordEntities;
import com.naosim.dddwork.kintai.datasource.csv.entity.AttendanceRecordEntity;
import com.naosim.dddwork.kintai.domain.aggregation.AggregationMonth;
import com.opencsv.exceptions.CsvException;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

// TODO: エラーハンドリング
// TODO: pathをconfigから読み込む
public class AttendanceCsvDao {

    private final CsvDao<AttendanceRecordEntity> csvDao;

    public AttendanceCsvDao() {
        this.csvDao = new CsvDao<>();
    }

    public AttendanceRecordEntities fetchAll() {
        List<AttendanceRecordEntity> records = null;
        try (Reader reader = Files.newBufferedReader(Paths.get("/Users/s-miyashita/Develop/git/kintai/src/main/resources/csv/attendance_data.csv"))) {
            records = csvDao.read(reader, AttendanceRecordEntity.class);
        } catch (IOException ie) {
            ie.printStackTrace();
        } catch (CsvException ce) {
            ce.printStackTrace();
        }
        return new AttendanceRecordEntities(records);
    }

    public AttendanceRecordEntities fetchMonthly(AggregationMonth aggregationMonth) {
        Map<LocalDate, AttendanceRecordEntity> recordMap = null;
        try (Reader reader = Files.newBufferedReader(Paths.get("/Users/s-miyashita/Develop/git/kintai/src/main/resources/csv/attendance_data.csv"))) {
            recordMap = csvDao
                    .read(reader, AttendanceRecordEntity.class)
                    .stream()
                    // TODO: 判定メソッド作る
                    .filter(rec -> aggregationMonth.getYearMonth().getYear() == rec.getAttendanceDate().getYear()
                            && aggregationMonth.getYearMonth().getMonth() == rec.getAttendanceDate().getMonth())
                    .collect(Collectors.toMap(
                            AttendanceRecordEntity::getAttendanceDate,
                            rec -> rec,
                            (oldVal, newVal) -> newVal,
                            HashMap::new
                    ));
        } catch (IOException ie) {
            ie.printStackTrace();
        } catch (CsvException ce) {
            ce.printStackTrace();
        }
        return new AttendanceRecordEntities(new ArrayList<>(recordMap.values()));
    }

    public void register(AttendanceRecordEntities records) {
        try (Writer writer = Files.newBufferedWriter(Paths.get("/Users/s-miyashita/Develop/git/kintai/src/main/resources/csv/attendance_data.csv"),
                                                     StandardOpenOption.APPEND
        )) {
            csvDao.writeAll(writer, records.getRecords());
        } catch (IOException ie) {
            ie.printStackTrace();
        } catch (CsvException ce) {
            ce.printStackTrace();
        }
    }

}
