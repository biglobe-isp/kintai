package com.naosim.dddwork.datasource;

import com.naosim.dddwork.domain.WorkDataRepository;
import com.naosim.dddwork.domain.WorkMinutes;
import com.naosim.dddwork.domain.WorkTime;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CsvRegister implements WorkDataRepository {
    private final String filePath;

    public CsvRegister(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void save(WorkTime workTime, WorkMinutes workMinutes, WorkMinutes overWorkMinutes) {
        File file = new File(filePath);
        try (FileWriter filewriter = new FileWriter(file, true)) {
            filewriter.write(String.format(
                    "%s,%s,%s,%s,%s,%s\n",
                    workTime.getWorkingDate(),
                    String.format("%02d", workTime.getWorkingStartHour().getHour())
                            + String.format("%02d", workTime.getWorkingStartMinute().getMinute()),
                    String.format("%02d", workTime.getWorkingEndHour().getHour())
                            + String.format("%02d", workTime.getWorkingEndMinute().getMinute()),
                    workMinutes,
                    overWorkMinutes,
                    workTime.getInputDate()
            ));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<WorkTime> findMonthWorkTime(YearMonth yearMonth) {
        File file = new File(filePath);

        try (
                FileReader fr = new FileReader(file);
                BufferedReader br = new BufferedReader(fr)
        ) {
            String line = br.readLine();
            Map<String, Integer> totalWorkMinutesMap = new HashMap<>();
            Map<String, Integer> totalOverWorkMinutesMap = new HashMap<>();
            while (line != null) {
                String[] columns = line.split(",");
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuuMM");
                if (!columns[0].startsWith(yearMonth.format(formatter))) {
                    continue;
                }
                totalWorkMinutesMap.put(columns[0], Integer.valueOf(columns[3]));
                totalOverWorkMinutesMap.put(columns[0], Integer.valueOf(columns[4]));

                line = br.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // List<>でよい？？
