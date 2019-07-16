package refactor.api;

import refactor.datasource.CsvFileRepository;
import refactor.domain.*;
import refactor.service.AttendanceAggregateService;
import refactor.service.AttendanceInputService;

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
                if (args.length < 2) {
                    throw new RuntimeException("引数が足りません");
                }

                AttendanceRepository attendanceRepository = new CsvFileRepository();
                AttendanceAggregateService attendanceAggregateService = new AttendanceAggregateService(
                        attendanceRepository);

                YearMonth yearMonth = new YearMonth(args[1]);
                String totalWorkingHoursText = String.format(
                        "勤務時間: %s", attendanceAggregateService.calculateTotalActualWorkingHours(yearMonth));
                String totalOvertimeHoursText = String.format(
                        "残業時間: %s", attendanceAggregateService.calculateTotalOvertimeHours(yearMonth));

                System.out.println(totalWorkingHoursText);
                System.out.println(totalOvertimeHoursText);

            } else {
                throw new RuntimeException("methodTypeが不正です");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static DailyAttendanceRecord createDailyAttendanceRecord(
            String yyyymmdd, String hhmmStart, String hhmmEnd) {
        WorkingDay workingDay = new WorkingDay(yyyymmdd);
        StartTime startTime = new StartTime(hhmmStart);
        EndTime endTime = new EndTime(hhmmEnd);
        AttendanceInputTime attendanceInputTime = new AttendanceInputTime();

        return new DailyAttendanceRecord(workingDay, startTime, endTime, attendanceInputTime);
    }
}