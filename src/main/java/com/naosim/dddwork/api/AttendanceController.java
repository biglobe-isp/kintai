package com.naosim.dddwork.api;

import com.naosim.dddwork.api.validator.ParamConverter;
import com.naosim.dddwork.domain.TimePoint;
import com.naosim.dddwork.domain.attendance.EndTime;
import com.naosim.dddwork.domain.attendance.StartTime;
import com.naosim.dddwork.api.attendancetime.NotVerifiedAttendanceTime;
import com.naosim.dddwork.api.attendancetime.VerifiedAttendanceTime;
import com.naosim.dddwork.domain.attendance.WorkDay;
import com.naosim.dddwork.domain.monthlysummary.MonthlySummary;
import com.naosim.dddwork.domain.monthlysummary.YearMonth;
import com.naosim.dddwork.exception.InvalidAttendanceException;
import com.naosim.dddwork.exception.ValidationException;
import com.naosim.dddwork.service.AttendanceService;
import com.naosim.dddwork.service.MonthlySummaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class AttendanceController {
    private final ParamConverter paramConverter;
    private final AttendanceService attendanceService;
    private final MonthlySummaryService monthlySummaryService;

    public String command(String[] args) {
        try {
            if (args.length < 1) {
                throw new ValidationException("引数が足りません");
            }
            String methodType = args[0];

            if ("input".equals(methodType)) {
                if (args.length < 4) {
                    throw new ValidationException("引数が足りません");
                }
                String inputWorkDay = args[1];
                String inputStartTime = args[2];
                String inputEndTime = args[3];
                return register(inputWorkDay, inputStartTime, inputEndTime);

            } else if ("total".equals(methodType)) {
                if (args.length < 2) {
                    throw new ValidationException("引数が足りません");
                }
                String inputYearMonth = args[1];
                return monthlyTotal(inputYearMonth);

            } else {
                throw new ValidationException("methodTypeが不正です");
            }
        } catch (Exception e) {
            return e.getClass().getName() + ": " +  e.getMessage();
        }
    }

    private String register(String inputWorkDay, String inputStartTime, String inputEndTime)
            throws InvalidAttendanceException, ValidationException {

        WorkDay workDay = paramConverter.convertWorkDay(inputWorkDay);
        TimePoint startTimePoint = paramConverter.convertTimePoint(inputStartTime);
        TimePoint endTimePoint = paramConverter.convertTimePoint(inputEndTime);

        NotVerifiedAttendanceTime notVerifiedAttendanceTime = NotVerifiedAttendanceTime.of(
                StartTime.of(startTimePoint), EndTime.of(endTimePoint));

        VerifiedAttendanceTime verifiedAttendanceTime = VerifiedAttendanceTime.of(notVerifiedAttendanceTime);

        attendanceService.registerAttendance(workDay, verifiedAttendanceTime);

        return "Success";
    }

    private String monthlyTotal(String inputYearMonth) throws ValidationException, IOException {
        YearMonth yearMonth = paramConverter.convertYearMonth(inputYearMonth);
        MonthlySummary monthlySummary = monthlySummaryService.acquireMonthlyTotal(yearMonth);

        return "勤務時間: " + monthlySummary.getWorkingHours().getMonthlyTotalString()
                + System.lineSeparator()
                + "残業時間: " + monthlySummary.getOverTimeHours().getMonthlyTotalString();
    }
}
