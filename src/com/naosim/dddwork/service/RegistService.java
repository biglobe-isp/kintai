package src.com.naosim.dddwork.service;

import src.com.naosim.dddwork.domain.*;
import src.com.naosim.dddwork.datasource.*;

public class RegistService {
    KintaiRepository repository;

    public void regist(String date, String start, String end) {
        System.out.println(String.format("service/regist params: date=%s start=%s end=%s", date, start, end));

        WorkingDate workingDate = new WorkingDate(date);
        StartTime startTime = new StartTime(Integer.valueOf(start.substring(0, 2)),Integer.valueOf(start.substring(2, 4)));
        EndTime endTime = new EndTime(Integer.valueOf(end.substring(0, 2)),Integer.valueOf(end.substring(2, 4)));
        WorkTime workTime = new WorkTime(startTime,endTime);
        OrverTime orverTime = new OrverTime(workTime);

        Regulation kintai = new Regulation(
                workingDate,
            startTime,
            endTime,
            workTime,
            orverTime);

        //勤怠登録
        KintaiRepository repository = new CsvOperat();
        try {
            repository.write(kintai);
        } catch (Exception e) {
            throw e;
        }
    }
}