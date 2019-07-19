package refactor.datasource;

import lombok.NonNull;
import refactor.domain.*;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;

public class CsvFileRepository implements AttendanceRepository {
    private static final String FILE_NAME = "data.csv";

    public MonthlyAttendanceRecord findByExtractionYearMonth(@NonNull ExtractionYearMonth extractionYearMonth) {
        Map<WorkingDay, DailyAttendanceRecord> monthlyAttendanceRecord = new HashMap<>();

        try (
                FileReader fr = new FileReader(new File(FILE_NAME));
                BufferedReader br = new BufferedReader(fr)
        ) {
            String line = br.readLine();

            while (line != null) {
                String[] columns = line.split(",");
                if (columns[0].startsWith(extractionYearMonth.toString())) {
                    WorkingDay date = WorkingDay.fromString(columns[0]);
                    StartTime startTime = StartTime.fromString(columns[1]);
                    EndTime endTime = EndTime.fromString(columns[2]);
                    AttendanceInputTime attendanceInputTime = new AttendanceInputTime(columns[5]);
                    DailyAttendanceRecord dailyAttendanceRecord = new DailyAttendanceRecord(
                            date, startTime, endTime, attendanceInputTime);
                    monthlyAttendanceRecord.put(date, dailyAttendanceRecord);
                }

                line = br.readLine();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("ファイルが存在しません");
        } catch (IOException e) {
            throw new RuntimeException("ファイルの読み込みに失敗しました");
        }

        List<DailyAttendanceRecord> dailyAttendanceRecords =
                monthlyAttendanceRecord.values().stream().collect(toList());
        return new MonthlyAttendanceRecord(dailyAttendanceRecords);
    }

    public void save(@NonNull DailyAttendanceRecord dailyAttendanceRecord) {
        try (FileWriter filewriter = new FileWriter(new File(FILE_NAME), true)) {
            filewriter.write(
                    String.format("%s,%s,%s,%s,%s,%s\n",
                            dailyAttendanceRecord.getWorkingDay(),
                            dailyAttendanceRecord.getStartTime(),
                            dailyAttendanceRecord.getEndTime(),
                            dailyAttendanceRecord.getActualWorkingHours().getMinutes(),
                            dailyAttendanceRecord.getOvertimeHours().getMinutes(),
                            dailyAttendanceRecord.getAttendanceInputTime().getTime()));
        } catch (IOException e) {
            throw new RuntimeException("ファイルへの書き込みに失敗しました");
        }

    }
}
