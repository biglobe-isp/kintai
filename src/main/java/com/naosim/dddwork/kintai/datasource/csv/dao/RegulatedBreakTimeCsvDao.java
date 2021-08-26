package com.naosim.dddwork.kintai.datasource.csv.dao;

import com.naosim.dddwork.kintai.datasource.csv.entity.RegulatedBreakTimeEntities;
import com.naosim.dddwork.kintai.datasource.csv.entity.RegulatedBreakTimeEntity;
import com.opencsv.exceptions.CsvException;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

// TODO: エラーハンドリング
// TODO: pathをconfigから読み込む
public class RegulatedBreakTimeCsvDao {

    private final CsvDao<RegulatedBreakTimeEntity> csvDao;

    public RegulatedBreakTimeCsvDao() {
        this.csvDao = new CsvDao<>();
    }

    public RegulatedBreakTimeEntities fetchAll() {
        List<RegulatedBreakTimeEntity> records = null;
        try (Reader reader = Files.newBufferedReader(Paths.get("/Users/s-miyashita/Develop/git/kintai/src/main/resources/csv/regulated_break_time_shift.csv"))) {
            records = csvDao.read(reader, RegulatedBreakTimeEntity.class);
        } catch (IOException ie) {
            ie.printStackTrace();
        } catch (CsvException ce) {
            ce.printStackTrace();
        }
        return new RegulatedBreakTimeEntities(records);
    }

}
