package refactor.datasource;

import refactor.domain.*;

import java.io.*;

public class CsvFileRepository implements AttendanceRepository {
    private static final String FILE_NAME = "data.csv";

    public MonthlyAttendanceRecord findByYearMonth(YearMonth yearMonth) {
        MonthlyAttendanceRecord monthlyAttendanceRecord = new MonthlyAttendanceRecord();

        try (
                FileReader fr = new FileReader(new File(FILE_NAME));
                BufferedReader br = new BufferedReader(fr)
        ) {
            String line = br.readLine();

            while (line != null) {
                String[] columns = line.split(",");
                if (columns[0].startsWith(yearMonth.toString())) {
                    WorkingDay date = new WorkingDay(columns[0]);
                    StartTime startTime = new StartTime(columns[1]);
                    EndTime endTime = new EndTime(columns[2]);
                    AttendanceInputTime attendanceInputTime = new AttendanceInputTime(columns[5]);
                    DailyAttendanceRecord dailyAttendanceRecord = new DailyAttendanceRecord(
                            date, startTime, endTime, attendanceInputTime);
                    monthlyAttendanceRecord.add(date, dailyAttendanceRecord);
                }

                line = br.readLine();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("ファイルが存在しません");
        } catch (IOException e) {
            throw new RuntimeException("ファイルの読み込みに失敗しました");
        }

        return monthlyAttendanceRecord;
    }

    public void save(DailyAttendanceRecord dailyAttendanceRecord) {
        try (FileWriter filewriter = new FileWriter(new File(FILE_NAME), true)) {
            filewriter.write(
                    String.format("%s,%s,%s,%s,%s,%s\n",
                            dailyAttendanceRecord.getWorkingDay().inYYYMMDD(),
                            dailyAttendanceRecord.getStartTime().inHHMM(),
                            dailyAttendanceRecord.getEndTime().inHHMM(),
                            dailyAttendanceRecord.getWorkingHours().inMinutes(),
                            dailyAttendanceRecord.getOvertimeHours().inMinutes(),
                            dailyAttendanceRecord.getAttendanceInputTime().now()));
        } catch (IOException e) {
            throw new RuntimeException("ファイルへの書き込みに失敗しました");
        }

    }
}
