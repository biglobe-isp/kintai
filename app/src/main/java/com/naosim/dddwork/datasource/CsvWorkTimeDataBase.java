package com.naosim.dddwork.datasource;

import com.naosim.dddwork.domain.WorkDataRepository;
import com.naosim.dddwork.domain.WorkTime;
import com.naosim.dddwork.domain.WorkTimeStorage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class CsvWorkTimeDataBase implements WorkDataRepository {
    private final String filePath;

    public CsvWorkTimeDataBase(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void save(WorkTime workTime, WorkTimeStorage storeWorkTime) {
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
                    storeWorkTime.getWorkMinutes(),
                    storeWorkTime.getOvertimeMinutes(),
                    LocalDateTime.now()
            ));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<WorkTimeStorage> findMonthWorkTime(YearMonth yearMonth) {
        File file = new File(filePath);
        List<WorkTimeStorage> specificMonthWorkTimes = new ArrayList<>();
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
                int totalWorkTime = Integer.parseInt(columns[3]);
                int totalOvertime = Integer.parseInt(columns[4]);
                WorkTimeStorage totalWorkTimeObj = new WorkTimeStorage(totalWorkTime, totalOvertime);

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
