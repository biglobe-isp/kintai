package kintai.datasource;

import kintai.domain.Attendance;
import kintai.domain.AttendanceTime;
import kintai.domain.OverWorkDuration;
import kintai.domain.WorkDuration;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

public class AttendanceMapperCsvImpl implements AttendanceMapperCsv {
    @Override
    public void save(String fileName,Attendance attendance) {
        File file = new File(fileName);
        try (FileWriter filewriter = new FileWriter(file, true)) {
            filewriter.write(String.format(
                    "%s,%s,%s,%s,%s,%s\n",
                    attendance.getDate(),
                    attendance.getAttendanceTime().getStart().toString(),
                    attendance.getAttendanceTime().getEnd().toString(),
                    attendance.getWorkDuration().getDuration().getSeconds(),
                    attendance.getOverWorkDuration().getDuration().getSeconds(),
                    LocalDateTime.now()
            ));
        } catch (IOException e) {
            throw new RuntimeException("CSV書き込み失敗");
        }
    }

    @Override
    public List<Attendance> findByYearMonth(String fileName,YearMonth yearMonth) {
        File file = new File(fileName);
        List<Attendance> attendanceList = new ArrayList<>();

        try (
                FileReader fr = new FileReader(file);
                BufferedReader br = new BufferedReader(fr)
        ) {
            String line = br.readLine();
            while (line != null) {
                String[] columns = line.split(",");
                if (columns[0].substring(0,7).equals(yearMonth.toString())) {
                    Attendance attendance = new Attendance(
                            LocalDate.parse(columns[0]),
                            new AttendanceTime(
                                    LocalDateTime.parse(columns[1]),
                                    LocalDateTime.parse(columns[2])
                            ),
                            new WorkDuration(Duration.ofSeconds(Long.parseLong(columns[3]))),
                            new OverWorkDuration(Duration.ofSeconds(Long.parseLong(columns[4])))
                    );
                    attendanceList.add(attendance);
                }
                line = br.readLine();
            }
            return attendanceList;
        } catch (IOException e) {
            throw new RuntimeException("CSV読み込み失敗");
        }
    }
}
