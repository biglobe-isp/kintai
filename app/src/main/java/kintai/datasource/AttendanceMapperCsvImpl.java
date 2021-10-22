package kintai.datasource;

import kintai.domain.Attendance;
import kintai.domain.AttendanceDate;
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
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class AttendanceMapperCsvImpl implements AttendanceMapperCsv {
    @Override
    public void save(String fileName,Attendance attendance) {
        File file = new File(fileName);
        try (FileWriter filewriter = new FileWriter(file, true)) {
            filewriter.write(String.format(
                    "%s,%s,%s,%s,%s,%s\n",
                    attendance.getAttendanceDate().format(),
                    attendance.getAttendanceTime().formatFrom(),
                    attendance.getAttendanceTime().formatTo(),
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
                System.out.println(columns[0].substring(0,8));
                System.out.println(yearMonth.toString());

                if (columns[0].substring(0,6).equals(yearMonth.format(DateTimeFormatter.ofPattern("yyyyMM")))) {
                    Attendance attendance = new Attendance(
                            new AttendanceDate(LocalDate.of(
                                    Integer.parseInt(columns[0].substring(0,4)),
                                    Integer.parseInt(columns[0].substring(4,6)),
                                    Integer.parseInt(columns[0].substring(6,8)))),
                            new AttendanceTime(
                                    LocalDateTime.of(Integer.parseInt(columns[1].substring(0,4)),
                                                     Integer.parseInt(columns[1].substring(4,6)),
                                                     Integer.parseInt(columns[1].substring(6,8)),
                                                     Integer.parseInt(columns[1].substring(8,10)),
                                                     Integer.parseInt(columns[1].substring(10,12))),
                                    LocalDateTime.of(Integer.parseInt(columns[2].substring(0,4)),
                                                     Integer.parseInt(columns[2].substring(4,6)),
                                                     Integer.parseInt(columns[2].substring(6,8)),
                                                     Integer.parseInt(columns[2].substring(8,10)),
                                                     Integer.parseInt(columns[2].substring(10,12)))),
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
