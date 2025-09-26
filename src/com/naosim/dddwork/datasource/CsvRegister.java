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
            filewriter.write(String.format(
                    "%s,%s,%s,%s,%s,%s\n",
                    workTime.getWorkingDate(),
                    String.format("%02d", workTime.getWorkingStartHour().getHour())
                            + String.format("%02d", workTime.getWorkingStartMinute().getMinute()),
                    String.format("%02d", workTime.getWorkingEndHour().getHour())
                            + String.format("%02d", workTime.getWorkingEndMinute().getMinute()),
                    storeWorkTime.getWorkMinutes(),
                    storeWorkTime.getOvertimeMinutes(),
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
            String line = br.readLine();
//            Map<String, Integer> totalWorkMinutesMap = new HashMap<>();
//            Map<String, Integer> totalOverWorkMinutesMap = new HashMap<>();
            while (line != null) {
                String[] columns = line.split(",");
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuuMM");
                if (!columns[0].startsWith(yearMonth.format(formatter))) {
                    continue;
                }
//                totalWorkMinutesMap.put(columns[0], Integer.valueOf(columns[3]));
//                totalOverWorkMinutesMap.put(columns[0], Integer.valueOf(columns[4]));
                WorkMinutes totalWorkTime = new WorkMinutes(Integer.parseInt(columns[3]));
                WorkMinutes totalOvertime = new WorkMinutes(Integer.parseInt(columns[4]));
                StoreWorkTime totalWorkTimeObj = new StoreWorkTime(totalWorkTime, totalOvertime);

                specificMonthWorkTimes.add(totalWorkTimeObj);

                line = br.readLine();
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
