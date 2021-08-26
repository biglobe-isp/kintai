package com.naosim.dddwork.kintai.datasource.csv.dao;

import com.naosim.dddwork.kintai.datasource.csv.entity.RegulatedWorkingTimeMinutesEntity;
import com.opencsv.exceptions.CsvException;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

// TODO: エラーハンドリング
// TODO: pathをconfigから読み込む
public class RegulatedWorkingTimeMinutesCsvDao {

    private final CsvDao<RegulatedWorkingTimeMinutesEntity> csvDao;

    public RegulatedWorkingTimeMinutesCsvDao() {
        this.csvDao = new CsvDao<>();
    }

    public RegulatedWorkingTimeMinutesEntity fetch() {
        RegulatedWorkingTimeMinutesEntity record = null;
        try (Reader reader = Files.newBufferedReader(Paths.get("/Users/s-miyashita/Develop/git/kintai/src/main/resources/csv/regulated_working_time_minutes.csv"))) {
            record = csvDao.read(reader, RegulatedWorkingTimeMinutesEntity.class).stream().findFirst().orElseThrow(RuntimeException::new);;
        } catch (IOException ie) {
            ie.printStackTrace();
        } catch (CsvException ce) {
            ce.printStackTrace();
        }
        return record;
    }

}
