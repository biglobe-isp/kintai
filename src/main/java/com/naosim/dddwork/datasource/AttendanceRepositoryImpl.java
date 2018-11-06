package com.naosim.dddwork.datasource;

import com.naosim.dddwork.domain.AttendanceRepository;
import com.naosim.dddwork.domain.ClosingHours;
import com.naosim.dddwork.domain.DailyAttendance;
import com.naosim.dddwork.domain.DailyAttendanceList;
import com.naosim.dddwork.domain.MonthlyAttendance;
import com.naosim.dddwork.domain.OvertimeHours;
import com.naosim.dddwork.domain.StartingHours;
import com.naosim.dddwork.domain.WorkDay;
import com.naosim.dddwork.domain.WorkingHours;
import com.naosim.dddwork.domain.use_case.TotalWorkingHoursApplication;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor

public class AttendanceRepositoryImpl implements AttendanceRepository {
    @Override
    public void register(DailyAttendance dailyAttendance) {
        File file = new File("Attendance.csv");

        try (FileWriter filewriter = new FileWriter(file, true)) {

            filewriter.write(
                    String.format(
                            "%s,%s,%s,%s,%s,%s\n",
                            dailyAttendance.getWorkDay().getValue().format(DateTimeFormatter.ofPattern("yyyyMMdd")),
                            dailyAttendance.getStartingHours().getValue().format(DateTimeFormatter.ofPattern("HHmm")),
                            dailyAttendance.getClosingHours().getValue().format(DateTimeFormatter.ofPattern("HHmm")),
                            dailyAttendance.getWorkingHours().getValue().toString(),
                            dailyAttendance.getOvertimeHours().getValue().toString(),
                            LocalDateTime.now().toString()
                    )
            );
        } catch (Exception exception) {
            exception.printStackTrace();
        }

    }

    @Override
    public MonthlyAttendance get(TotalWorkingHoursApplication totalWorkingHoursApplication) {
        File file = new File("Attendance.csv");


        try (
                FileReader fr = new FileReader(file);
                BufferedReader br = new BufferedReader(fr)
        ) {
            Map<String, String[]> laborRegulations = new LinkedHashMap<>();

            br.lines()
                    .map(line -> line.split(","))
                    .forEach(columns ->
                            laborRegulations.put(columns[0], columns)
                    );

            return new MonthlyAttendance(
                    totalWorkingHoursApplication.getWorkingMonth(),
                    new DailyAttendanceList(
                            laborRegulations.values().stream()
                                    .filter(columns ->
                                            columns[0].substring(0, 6).equals(
                                                    totalWorkingHoursApplication.getWorkingMonth().getValue().format(DateTimeFormatter.ofPattern("yyyyMM"))
                                            )
                                    )
                                    .map(columns ->
                                            new DailyAttendance(
                                                    new WorkDay(LocalDate.parse(columns[0], DateTimeFormatter.ofPattern("yyyyMMdd"))),
                                                    new StartingHours(LocalTime.parse(columns[1], DateTimeFormatter.ofPattern("HHmm"))),
                                                    new ClosingHours(LocalTime.parse(columns[2], DateTimeFormatter.ofPattern("HHmm"))),
                                                    new WorkingHours(Integer.parseInt(columns[3])),
                                                    new OvertimeHours(Integer.parseInt(columns[4]))
                                            )
                                    ).collect(Collectors.toList())
                    )
            );

        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return null;
    }
}
