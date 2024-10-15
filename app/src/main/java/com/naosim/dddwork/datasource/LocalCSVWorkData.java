package com.naosim.dddwork.datasource;

import com.naosim.dddwork.domain.DailyWorkDataList;
import com.naosim.dddwork.domain.WorkDataRepository;
import com.naosim.dddwork.domain.daily_work.*;

import java.io.*;
import java.text.ParseException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

/**
 * ローカルCSVファイルから勤務記録入出力
 */
public class LocalCSVWorkData implements WorkDataRepository {
    @Override
    public DailyWorkData writeDailyWorkData(
            WorkDate workDate,
            StartWorkTime startWorkTime,
            EndWorkTime endWorkTime) {
        File localFile = new File(new WorkDataFilePath().getFilePath());

        DailyWorkData writeData = new DailyWorkData(workDate, startWorkTime, endWorkTime);

        String inputData = String.format(
                "%s,%s,%s\n",
                writeData.getWorkDate().getValue(),
                writeData.getTotalWorkTimes().getValue(),
                LocalDateTime.now()
        );

        try (FileWriter filewriter = new FileWriter(localFile, true)) {
            filewriter.write(inputData);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return writeData;
    }

    @Override
    public DailyWorkDataList fetchDailyWorkData(AggregationYearMonth aggregationYearMonth) {
        DailyWorkDataList workDataList = new DailyWorkDataList();
        File file = new File(new WorkDataFilePath().getFilePath());

        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);

            String line = br.readLine();

            while (line != null) {
                DailyWorkData data = convertToDailyWorkData(line);

                if(checkAddWorkDataListNecessity(data, aggregationYearMonth)) workDataList.addDailyWorkData(data);

                line = br.readLine();
            }

            return workDataList;
        } catch (FileNotFoundException e) {
            throw new RuntimeException("勤務記録データを発見できません。");
        } catch (IOException e) {
            throw new RuntimeException("勤務記録データの読み込みに失敗しました。");
        }
    }

    private DailyWorkData convertToDailyWorkData(String line) {
        String[] columns = line.split(",");
        try {
            WorkDate workDate = parseToWorkDate(columns[0]);
            Duration workingHours = parseDailyWorkingHours(columns[1]);

            return new DailyWorkData(workDate, workingHours);
        } catch (ParseException e) {
            throw new RuntimeException("勤務記録データが破損しています。");
        }
    }

    private WorkDate parseToWorkDate(String line) throws ParseException {
        return new WorkDate(LocalDate.parse(line));
    }

    private Duration parseDailyWorkingHours(String line) throws ParseException {
        return Duration.parse(line);
    }

    private boolean checkAddWorkDataListNecessity(DailyWorkData workData, AggregationYearMonth aggregationYearMonth) {
        LocalDate aggregationStartDate = LocalDate.of(
                aggregationYearMonth.getValue().getYear(),
                aggregationYearMonth.getValue().getMonth(),
                1);
        LocalDate aggregationEndDate = aggregationYearMonth.getValue().with(
                TemporalAdjusters.lastDayOfMonth());

        return !(aggregationStartDate.isAfter(workData.getWorkDate().getValue())
                || aggregationEndDate.isBefore(workData.getWorkDate().getValue()));
    }
}