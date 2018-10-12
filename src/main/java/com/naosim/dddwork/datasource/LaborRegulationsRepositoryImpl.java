package com.naosim.dddwork.datasource;

import com.naosim.dddwork.domain.BreakTime;
import com.naosim.dddwork.domain.BreakTimeList;
import com.naosim.dddwork.domain.FixedTime;
import com.naosim.dddwork.domain.LaborRegulations;
import com.naosim.dddwork.domain.LaborRegulationsRepository;
import com.naosim.dddwork.domain.use_case.WorkTimeRegistrationApplication;
import com.naosim.dddwork.domain.ApplyEndDate;
import com.naosim.dddwork.domain.ApplyStartDate;
import com.naosim.dddwork.domain.BreakEndTime;
import com.naosim.dddwork.domain.BreakStartTime;
import com.naosim.dddwork.domain.ClosingTime;
import com.naosim.dddwork.domain.StartingTime;
import com.naosim.dddwork.domain.WorkDay;
import com.naosim.dddwork.domain.WorkingTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class LaborRegulationsRepositoryImpl implements LaborRegulationsRepository {
    @Override
    public LaborRegulations get(WorkTimeRegistrationApplication workTimeRegistrationApplication) {
        FixedTime fixedTime = getFixedTime();
        BreakTimeList breakTimeList = getBreakTimeList(workTimeRegistrationApplication.getWorkDay());
        return new LaborRegulations(fixedTime, breakTimeList);
    }

    static private FixedTime getFixedTime() {
        File fileFixedTime = new File("FixedTime.csv");

        try(
                FileReader fr = new FileReader(fileFixedTime);
                BufferedReader br = new BufferedReader(fr)
        ) {
            Map<String,String[]> fixedTimeMap = new LinkedHashMap<>();

            String[] columns = br.readLine().split(",");

            return new FixedTime(
                    new StartingTime(LocalTime.parse(columns[0], DateTimeFormatter.ofPattern("HHmm"))),
                    new ClosingTime(LocalTime.parse(columns[1], DateTimeFormatter.ofPattern("HHmm"))),
                    new WorkingTime(Integer.parseInt(columns[2]))
            );

        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return null;
    }


    static private BreakTimeList getBreakTimeList(WorkDay workDay) {
        File fileBreakTime = new File("BreakTime.csv");

        try(
                FileReader fr = new FileReader(fileBreakTime);
                BufferedReader br = new BufferedReader(fr)
        ) {
            Map<String, String[]> breakTimeMap = new LinkedHashMap<>();

            br.lines()
                    .map(line -> line.split(","))
                    .forEach(columns ->
                            breakTimeMap.put(columns[0] + columns[1] + columns[2] + columns[3], columns)
                    );

            return new BreakTimeList(
                    breakTimeMap.values().stream()
                            .filter(columns -> !(LocalDate.parse(columns[0], DateTimeFormatter.ofPattern("yyyyMMdd")).isAfter(workDay.getValue())
                                                 || LocalDate.parse(columns[1], DateTimeFormatter.ofPattern("yyyyMMdd")).isBefore(workDay.getValue()))
                            )
                            .map(columns ->
                                new BreakTime(
                                        new ApplyStartDate(LocalDate.parse(columns[0], DateTimeFormatter.ofPattern("yyyyMMdd"))),
                                        new ApplyEndDate(LocalDate.parse(columns[1], DateTimeFormatter.ofPattern("yyyyMMdd"))),
                                        new BreakStartTime(LocalTime.parse(columns[2], DateTimeFormatter.ofPattern("HHmm"))),
                                        new BreakEndTime(LocalTime.parse(columns[3], DateTimeFormatter.ofPattern("HHmm")))
                                )
                            ).collect(Collectors.toList())
            );

        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return null;
    }
}
