package com.naosim.dddwork.api;

import com.naosim.dddwork.domain.TimePoint;
import com.naosim.dddwork.domain.attendance.EndTime;
import com.naosim.dddwork.domain.attendance.NotVerifiedAttendanceTime;
import com.naosim.dddwork.domain.attendance.StartTime;
import com.naosim.dddwork.domain.attendance.WorkDay;
import com.naosim.dddwork.domain.monthlysummary.YearMonth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.naosim.dddwork")
public class Main implements CommandLineRunner {

    private final RegisterController registerController;
    private final MonthlyTotalController monthlyTotalController;

    @Autowired
    public Main(RegisterController registerController, MonthlyTotalController monthlyTotalController) {
        this.registerController = registerController;
        this.monthlyTotalController = monthlyTotalController;
    }

    public static void main(String[] args) {
// リファクタ前
//        com.naosim.dddwork.Main oldMain = new com.naosim.dddwork.Main();
//        oldMain.main(args);

        // リファクタ後
        SpringApplication springApplication = new SpringApplication(Main.class);
        springApplication.setBannerMode(Banner.Mode.OFF);
        springApplication.run(args);
    }

    @Override
    public void run(String... args) throws Exception {
        try {
            if (args.length < 1) {
                throw new RuntimeException("引数が足りません");
            }
            String methodType = args[0];

            if ("input".equals(methodType)) {
                register(args);

            } else if ("total".equals(methodType)) {
                monthlyTotal(args);

            } else {
                throw new RuntimeException("methodTypeが不正です");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void register(String[] args) {
        if (args.length < 4) {
            throw new RuntimeException("引数が足りません");
        }

        String inputWorkDay = args[1];
        String inputStartTime = args[2];
        String inputEndTime = args[3];

        DateValidator dateValidator = DateValidator.of(inputWorkDay);
        if (!dateValidator.isValid(EnumDateFormat.yyyyMMdd)) {
            throw new RuntimeException("日付の形式が正しくありません");
        }

        TimeValidator startTimeValidator = TimeValidator.of(inputStartTime);
        TimeValidator endTimeValidator = TimeValidator.of((inputEndTime));
        if (!startTimeValidator.isValid() || !endTimeValidator.isValid()) {
            throw new RuntimeException("時刻の形式が正しくありません");
        }

        WorkDay workDay = WorkDay.of(inputWorkDay);
        NotVerifiedAttendanceTime notVerifiedAttendanceTime = NotVerifiedAttendanceTime.of(
                StartTime.of(TimePoint.of(Integer.parseInt(inputStartTime.substring(0, 2)),
                                          Integer.parseInt(inputStartTime.substring(2)))),
                EndTime.of(TimePoint.of(Integer.parseInt(inputEndTime.substring(0, 2)),
                                        Integer.parseInt(inputEndTime.substring(2)))));

        registerController.register(workDay, notVerifiedAttendanceTime);
    }

    private void monthlyTotal(String[] args) {
        if (args.length < 2) {
            throw new RuntimeException("引数が足りません");
        }

        String inputYearMonth = args[1];
        DateValidator dateValidator = DateValidator.of(inputYearMonth);
        if (dateValidator.isValid(EnumDateFormat.yyyyMM)) {
            throw new RuntimeException("年月の形式が正しくありません");
        }

        YearMonth yearMonth = YearMonth.of(Integer.parseInt(inputYearMonth.substring(0, 4)),
                                           Integer.parseInt(inputYearMonth.substring(4, 6)));
        monthlyTotalController.monthlyTotal(yearMonth);
    }
}
