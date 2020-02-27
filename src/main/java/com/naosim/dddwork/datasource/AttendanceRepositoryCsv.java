package com.naosim.dddwork.datasource;

import com.naosim.dddwork.domain.AttendanceRepository;
import com.naosim.dddwork.domain.IAttendanceFactory;
import com.naosim.dddwork.domain.attendance.Attendance;
import com.naosim.dddwork.domain.monthlysummary.YearMonth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
public class AttendanceRepositoryCsv implements AttendanceRepository {

    private final IAttendanceFactory iAttendanceFactory;

    @Autowired
    public AttendanceRepositoryCsv(IAttendanceFactory iAttendanceFactory) {
        this.iAttendanceFactory = iAttendanceFactory;
    }

    @Override
    public void save(Attendance attendance) {
        try {
            File file = getCsvFile();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");

            String workday = attendance.getWorkDay().format(formatter);
            String start = attendance.getAttendanceTime().getStartTime().getTimePoint().toString();
            String end = attendance.getAttendanceTime().getEndTime().getTimePoint().toString();
            String workingHours = String.valueOf(attendance.getWorkingHours().getTotalMinutes());
            String overTimeHours = String.valueOf(attendance.getOverTimeHours().getTotalMinutes());
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
                System.out.println("line:" + line);

                while (line != null) {
                    String[] columns = line.split(",");
                    System.out.println("columns[0]:" + columns[0]);
                    System.out.println("yearMonth:" + yearMonth);

                    if (!columns[0].startsWith(yearMonth.toString())) {
                        line = br.readLine();
                        continue;
                    }

                    attendanceList.add(iAttendanceFactory.createFromCsv(
                            columns[0], columns[1], columns[2], columns[4], columns[4]
                    ));

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
}
