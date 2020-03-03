package com.naosim.dddwork.api;

import com.google.common.base.Strings;
import com.naosim.dddwork.domain.IAttendanceFactory;
import com.naosim.dddwork.domain.TimePoint;
import com.naosim.dddwork.domain.TimeUnit;
import com.naosim.dddwork.domain.WorkRegulationsRepository;
import com.naosim.dddwork.domain.attendance.Attendance;
import com.naosim.dddwork.domain.attendance.AttendanceTime;
import com.naosim.dddwork.domain.attendance.EndTime;
import com.naosim.dddwork.domain.attendance.StartTime;
import com.naosim.dddwork.domain.attendance.WorkDay;
import com.naosim.dddwork.domain.monthlysummary.MonthlySummary;
import com.naosim.dddwork.domain.monthlysummary.YearMonth;
import com.naosim.dddwork.domain.workregulations.WorkRegulations;
import com.naosim.dddwork.service.AttendanceService;
import com.naosim.dddwork.service.MonthlySummaryService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SpringBootApplication
@ComponentScan("com.naosim.dddwork")
public class Main {

    public static void main(String[] args) {
// リファクタ前
//        com.naosim.dddwork.Main oldMain = new com.naosim.dddwork.Main();
//        oldMain.main(args);

        // リファクタ後
        try (ConfigurableApplicationContext ctx = SpringApplication.run(Main.class, args)) {
            if (args.length < 1) {
                throw new RuntimeException("引数が足りません");
            }
            String methodType = args[0];

            if ("input".equals(methodType)) {
                register(ctx, args);

            } else if ("total".equals(methodType)) {
                monthlyTotal(ctx, args);

            } else {
                throw new RuntimeException("methodTypeが不正です");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void register(ConfigurableApplicationContext ctx, String[] args) {
        if (args.length < 4) {
            throw new RuntimeException("引数が足りません");
        }

        if (!isValidDate(args[1], "yyyyMMdd")) {
            throw new RuntimeException("日付の形式が正しくありません");
        }

        if (!isValidTime(args[2]) || !isValidTime(args[3])) {
            throw new RuntimeException("時刻の形式が正しくありません");
        }

        WorkDay workDay = WorkDay.of(args[1]);
        StartTime startTime = StartTime.of(TimePoint.of(Integer.parseInt(args[2].substring(0, 2)),
                                                        Integer.parseInt(args[2].substring(2))));
        EndTime endTime = EndTime.of(TimePoint.of(Integer.parseInt(args[3].substring(0, 2)),
                                                  Integer.parseInt(args[3].substring(2))));

        if (startTime.getTimePoint().getIntValue() > endTime.getTimePoint().getIntValue()) {
            throw new RuntimeException("開始時刻＞終了時刻は登録できません");
        }

        AttendanceTime attendanceTime = AttendanceTime.of(startTime, endTime);

        WorkRegulationsRepository workRegulationsRepository = ctx.getBean(WorkRegulationsRepository.class);
        WorkRegulations workRegulations = workRegulationsRepository.getCurrentRegulations();

        IAttendanceFactory attendanceFactory = ctx.getBean(IAttendanceFactory.class);
        Attendance attendance = attendanceFactory.createForRegister(workDay, attendanceTime, workRegulations);

        if (attendance == null) {
            throw new RuntimeException("遅刻は認めません");
        }

        AttendanceService attendanceService = ctx.getBean(AttendanceService.class);
        attendanceService.registerAttendance(attendance);
    }

    private static void monthlyTotal(ConfigurableApplicationContext ctx, String[] args) {
        if (args.length < 2) {
            throw new RuntimeException("引数が足りません");
        }

        if (!isValidDate(args[1], "yyyyMM")) {
            throw new RuntimeException("年月の形式が正しくありません");
        }

        int year = Integer.parseInt(args[1].substring(0, 4));
        int month = Integer.parseInt(args[1].substring(4, 6));

        YearMonth yearMonth = YearMonth.of(year, month);
        MonthlySummaryService monthlySummaryService = ctx.getBean(MonthlySummaryService.class);
        MonthlySummary monthlySummary = monthlySummaryService.acquireMonthlyTotal(yearMonth);

        System.out.println("勤務時間: " + convertMonthlyTotalString(monthlySummary.getWorkingHours()));
        System.out.println("残業時間: " + convertMonthlyTotalString(monthlySummary.getOverTimeHours()));
    }

    private static String convertMonthlyTotalString(TimeUnit value) {
        return value.getHour() + "時間" + value.getMinutes() + "分";
    }

    private static boolean isValidDate(String input, String format) {
        if (input.length() != format.length()) {
            return false;
        }

        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            sdf.setLenient(false);
            sdf.parse(input);

            return true;

        } catch(Exception ex) {
            return false;
        }
    }

    private static boolean isValidTime(String inputTime) {
        if (Strings.isNullOrEmpty(inputTime))
            return false;
        Pattern p = Pattern.compile("^([0-1][0-9]|[2][0-9])[0-5][0-9]$");
        Matcher m = p.matcher(inputTime);
        if ( !m.find() )
            return false;
        return true;
    }
}
