package kintai.datasource;

import kintai.domain.Attendance;
import kintai.domain.AttendanceDate;
import kintai.domain.AttendanceTime;
import kintai.domain.OverWorkDuration;
import kintai.domain.WorkDuration;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static java.nio.file.StandardOpenOption.CREATE;

public class AttendanceMapperCsvImpl implements AttendanceMapperCsv {
    @Override
    public void save(Path path, Attendance attendance) {
        try (BufferedWriter fileWriter = Files.newBufferedWriter(path, CREATE)) {
            fileWriter.write(String.format(
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
    public List<Attendance> findByYearMonth(Path path,YearMonth yearMonth) {
        List<Attendance> attendanceList = new ArrayList<>();
        try (
                BufferedReader br = Files.newBufferedReader(path)
        ) {
            String line = br.readLine();
            while (line != null) {
                String[] columns = line.split(",");

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
