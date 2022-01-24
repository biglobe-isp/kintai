package src.com.naosim.dddwork.service;

import java.util.*;

import src.com.naosim.dddwork.domain.*;
import src.com.naosim.dddwork.datasource.*;

public class RegistService {
    KintaiRepository repository;

    public void regist(String date, String start, String end) {
        System.out.println(String.format("service/regist params: date=%s start=%s end=%s", date, start, end));

        //勤怠算出
        Regulation kintai = new Regulation(
            date,
            Integer.valueOf(start.substring(0, 2)),
            Integer.valueOf(start.substring(2, 4)),
            Integer.valueOf(end.substring(0, 2)),
            Integer.valueOf(end.substring(2, 4)));
        kintai.workTime();
        kintai.overTime();

        //勤怠登録
        KintaiRepository repository = new CsvOperat();
        try {
            repository.write(
                    kintai.getDate(),
                    kintai.getStart(),
                    kintai.getEnd(),
                    kintai.getWorkMinutes(),
                    kintai.getOverWorkMinutes());
        } catch (Exception e) {
            throw e;
        }
    }
}