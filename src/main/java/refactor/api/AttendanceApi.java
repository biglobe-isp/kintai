package refactor.api;

import refactor.datasource.CsvFileRepository;
import refactor.domain.*;
import refactor.service.AttendanceInputService;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class AttendanceApi {
    public static void main(String[] args) {
        try {
            if (args.length < 1) {
                throw new RuntimeException("引数が足りません");
            }
            String methodType = args[0];

            if ("input".equals(methodType)) {
                if (args.length < 4) {
                    throw new RuntimeException("引数が足りません");
                }

                DailyAttendanceRecord dailyAttendanceRecord = createDailyAttendanceRecord(args[1], args[2], args[3]);
                AttendanceRepository repository = new CsvFileRepository();

                AttendanceInputService attendanceInputService = new AttendanceInputService(
                        dailyAttendanceRecord, repository);
                attendanceInputService.inputAttendance();

            } else if ("total".equals(methodType)) {
                String yearMonth = args[1];
                if (args.length < 2) {
                    throw new RuntimeException("引数が足りません");
                }

                int totalWorkMinutes = 0;
                int totalOverWorkMinutes = 0;

                File file = new File("data.csv");

                try (
                        FileReader fr = new FileReader(file);
                        BufferedReader br = new BufferedReader(fr);
                ) {

                    String line = br.readLine();
                    Map<String, Integer> totalWorkMinutesMap = new HashMap<>();
                    Map<String, Integer> totalOverWorkMinutesMap = new HashMap<>();
                    while (line != null) {
                        String[] columns = line.split(",");
                        if (!columns[0].startsWith(yearMonth)) {
                            continue;
                        }
                        totalWorkMinutesMap.put(columns[0], Integer.valueOf(columns[3]));
                        totalOverWorkMinutesMap.put(columns[0], Integer.valueOf(columns[4]));

                        line = br.readLine();
                    }

                    Set<String> keySet = totalWorkMinutesMap.keySet();
                    for (String key : keySet) {
                        totalWorkMinutes += totalWorkMinutesMap.get(key);
                        totalOverWorkMinutes += totalOverWorkMinutesMap.get(key);
                    }

                    System.out.println("勤務時間: " + totalWorkMinutes / 60 + "時間" + totalWorkMinutes % 60 + "分");
                    System.out.println("残業時間: " + totalOverWorkMinutes / 60 + "時間" + totalOverWorkMinutes % 60 + "分");
                }

            } else {
                throw new RuntimeException("methodTypeが不正です");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static DailyAttendanceRecord createDailyAttendanceRecord(
            String yyyymmdd, String hhmmStart, String hhmmEnd) {
        Date date = createDate(yyyymmdd);
        StartTime startTime = createStartTime(hhmmStart);
        EndTime endTime = createEndTime(hhmmEnd);
        BreakTime breakTime = new BreakTime(endTime);
        WorkingHours workingHours = new WorkingHours(startTime, endTime, breakTime);
        OvertimeHours overtimeHours = new OvertimeHours(workingHours);
        CurrentTime currentTime = new CurrentTime();

        return new DailyAttendanceRecord(
                date, startTime, endTime, workingHours, overtimeHours, breakTime, currentTime);
    }

    private static Date createDate(String yyyymmdd) {
        int year = Integer.valueOf(yyyymmdd.substring(0, 4));
        int month = Integer.valueOf(yyyymmdd.substring(4, 6));
        int day = Integer.valueOf(yyyymmdd.substring(6, 8));
        return new Date(year, month, day);
    }

    private static StartTime createStartTime(String hhmm) {
        int hour = Integer.valueOf(hhmm.substring(0, 2));
        int minute = Integer.valueOf(hhmm.substring(2, 4));
        return new StartTime(hour, minute);
    }

    private static EndTime createEndTime(String hhmm) {
        int hour = Integer.valueOf(hhmm.substring(0, 2));
        int minute = Integer.valueOf(hhmm.substring(2, 4));
        return new EndTime(hour, minute);
    }
}