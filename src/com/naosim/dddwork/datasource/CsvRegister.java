package com.naosim.dddwork.datasource;

import com.naosim.dddwork.domain.StoreWorkTime;
import com.naosim.dddwork.domain.WorkDataRepository;
import com.naosim.dddwork.domain.WorkMinutes;
import com.naosim.dddwork.domain.WorkTime;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class CsvRegister implements WorkDataRepository {
    private final String filePath;

    public CsvRegister(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void save(WorkTime workTime, StoreWorkTime storeWorkTime) {
        File file = new File(filePath);
        try (FileWriter filewriter = new FileWriter(file, true)) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
            String formattedDate = workTime.getWorkingDate().format(formatter);
            filewriter.write(String.format(
                    "%s,%s,%s,%d,%d,%s\n",
                    formattedDate,
                    String.format("%02d", workTime.getWorkingStartHour().getHour())
                            + String.format("%02d", workTime.getWorkingStartMinute().getMinute()),
                    String.format("%02d", workTime.getWorkingEndHour().getHour())
                            + String.format("%02d", workTime.getWorkingEndMinute().getMinute()),
                    storeWorkTime.getWorkMinutes().getWorkMinutes(),
                    storeWorkTime.getOvertimeMinutes().getWorkMinutes(),
                    workTime.getInputDate()
            ));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<StoreWorkTime> findMonthWorkTime(YearMonth yearMonth) {
        File file = new File(filePath);
        List<StoreWorkTime> specificMonthWorkTimes = new ArrayList<>();
        try (
                FileReader fr = new FileReader(file);
                BufferedReader br = new BufferedReader(fr)
        ) {
            String line;
//            String line = br.readLine();
            while ((line = br.readLine()) != null) {
                String[] columns = line.split(",");
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuuMM");
                String yearMonthDate = yearMonth.format(formatter);
                if (!columns[0].startsWith(yearMonthDate)) {
                    continue;
                }
                WorkMinutes totalWorkTime = new WorkMinutes(Integer.parseInt(columns[3]));
                WorkMinutes totalOvertime = new WorkMinutes(Integer.parseInt(columns[4]));
                StoreWorkTime totalWorkTimeObj = new StoreWorkTime(totalWorkTime, totalOvertime);

                specificMonthWorkTimes.add(totalWorkTimeObj);

//                line = br.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return specificMonthWorkTimes;
    }
}

// List<>でよい？？

// 指定した月の１日ずつの時間のリストを返したい
// 残業時間も同じく
// それら二つを返すにはクラスを新しく作り、そのクラスをかえす？
