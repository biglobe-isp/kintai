package com.naosim.dddwork.datasource;

import com.naosim.dddwork.domain.AttendanceRepository;
import com.naosim.dddwork.domain.TimePoint;
import com.naosim.dddwork.domain.TimeUnit;
import com.naosim.dddwork.domain.attendance.*;
import com.naosim.dddwork.api.attendanceTime.VerifiedAttendanceTime;
import com.naosim.dddwork.domain.monthlysummary.YearMonth;
import com.naosim.dddwork.domain.service.AttendanceGeneratableForRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class AttendanceRepositoryCsv implements AttendanceRepository {

    private final AttendanceGeneratableForRepository attendanceGenerator;

    @Override
    public void save(Attendance attendance) {
        try {
            File file = getCsvFile();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");

            String workday = attendance.getWorkDay().getDate().format(formatter);

            String start = attendance.getAttendanceTime().getStartTime().getTimePoint().toString();
            String end = attendance.getAttendanceTime().getEndTime().getTimePoint().toString();

            String workingHours = String.valueOf(attendance.getWorkingHours().getTimeUnit().getTotalMinutes());
            String overTimeHours = String.valueOf(attendance.getOverTimeHours().getTimeUnit().getTotalMinutes());
            String now = LocalDateTime.now().toString();

            try (FileWriter filewriter = new FileWriter(file, true)) {
                filewriter.write(String.format("%s,%s,%s,%s,%s,%s\n",
                                               workday, start, end, workingHours, overTimeHours, now
                ));
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public List<Attendance> findSpecifiedYearMonth(YearMonth yearMonth) {
        List<Attendance> attendanceList = new ArrayList<>();
        try {
            File file = getCsvFile();

            try (
                    FileReader fr = new FileReader(file);
                    BufferedReader br = new BufferedReader(fr);
            ) {
                String line = br.readLine();

                while (line != null) {
                    String[] columns = line.split(",");

                    if (!columns[0].startsWith(yearMonth.toString())) {
                        line = br.readLine();
                        continue;
                    }

                    attendanceList.add(generateAttendance(
                            columns[0], columns[1], columns[2], columns[3], columns[4]));

                    line = br.readLine();
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return attendanceList;
    }

    private static File getCsvFile() {
        return new File("data.csv");
    }

    private Attendance generateAttendance(String workD, String start, String end, String workingH, String OverTimeH) {
        WorkDay workDay = WorkDay.of(workD);

        TimePoint startTime = TimePoint.of(start);
        TimePoint endTime = TimePoint.of(end);
        AttendanceTime attendanceTime = AttendanceTime.of(new VerifiedAttendanceTime(
                StartTime.of(startTime), EndTime.of(endTime)));

        WorkingHours workingHours = WorkingHours.of(TimeUnit.of(Integer.parseInt(workingH)));
        OverTimeHours overTimeHours = OverTimeHours.of(TimeUnit.of(Integer.parseInt(OverTimeH)));

        return attendanceGenerator.create(workDay, attendanceTime, workingHours, overTimeHours);
    }
}
