package com.naosim.dddwork.api;

import com.naosim.dddwork.api.validator.ParamConverter;
import com.naosim.dddwork.domain.TimePoint;
import com.naosim.dddwork.domain.attendance.EndTime;
import com.naosim.dddwork.domain.attendance.StartTime;
import com.naosim.dddwork.api.attendanceTime.NotVerifiedAttendanceTime;
import com.naosim.dddwork.api.attendanceTime.VerifiedAttendanceTime;
import com.naosim.dddwork.domain.attendance.WorkDay;
import com.naosim.dddwork.domain.monthlysummary.MonthlySummary;
import com.naosim.dddwork.domain.monthlysummary.YearMonth;
import com.naosim.dddwork.service.AttendanceService;
import com.naosim.dddwork.service.MonthlySummaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class AttendanceController {
    private final ParamConverter paramConverter;
    private final AttendanceService attendanceService;
    private final MonthlySummaryService monthlySummaryService;

    public void command(String[] args) {
        try {
            if (args.length < 1) {
                throw new RuntimeException("引数が足りません");
            }
            String methodType = args[0];

            if ("input".equals(methodType)) {
                if (args.length < 4) {
                    throw new RuntimeException("引数が足りません");
                }
                String inputWorkDay = args[1];
                String inputStartTime = args[2];
                String inputEndTime = args[3];
                register(inputWorkDay, inputStartTime, inputEndTime);

            } else if ("total".equals(methodType)) {
                if (args.length < 2) {
                    throw new RuntimeException("引数が足りません");
                }
                String inputYearMonth = args[1];
                monthlyTotal(inputYearMonth);

            } else {
                throw new RuntimeException("methodTypeが不正です");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void register(String inputWorkDay, String inputStartTime, String inputEndTime) {

        WorkDay workDay = paramConverter.convertWorkDay(inputWorkDay);
        TimePoint startTimePoint = paramConverter.convertTimePoint(inputStartTime);
        TimePoint endTimePoint = paramConverter.convertTimePoint(inputEndTime);

        NotVerifiedAttendanceTime notVerifiedAttendanceTime = NotVerifiedAttendanceTime.of(
                StartTime.of(startTimePoint), EndTime.of(endTimePoint));

        VerifiedAttendanceTime verifiedAttendanceTime = VerifiedAttendanceTime.of(notVerifiedAttendanceTime);

        attendanceService.registerAttendance(workDay, verifiedAttendanceTime);
    }

    private void monthlyTotal(String inputYearMonth) {
        YearMonth yearMonth = paramConverter.convertYearMonth(inputYearMonth);
        MonthlySummary monthlySummary = monthlySummaryService.acquireMonthlyTotal(yearMonth);

        System.out.println("勤務時間: " + monthlySummary.getWorkingHours().getMonthlyTotalString());
        System.out.println("残業時間: " + monthlySummary.getOverTimeHours().getMonthlyTotalString());
    }
}
