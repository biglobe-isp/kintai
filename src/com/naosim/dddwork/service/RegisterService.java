package src.com.naosim.dddwork.service;

import src.com.naosim.dddwork.domain.*;
import src.com.naosim.dddwork.datasource.*;

public class RegisterService {

    public void register(String date, int startHour, int startMinute, int endHour, int endMinute) {
        WorkingDate workingDate = new WorkingDate(date);
        StartTime startTime = new StartTime(startHour,startMinute);
        EndTime endTime = new EndTime(endHour,endMinute);
        WorkTime workTime = new WorkTime(startTime,endTime);
        OverTime orverTime = new OverTime(workTime);

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