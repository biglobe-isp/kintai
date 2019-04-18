package com.naosim.dddwork.api;

import com.naosim.dddwork.domain.AttendanceSummary;
import com.naosim.dddwork.domain.TimePoint;
import com.naosim.dddwork.domain.WorkMinute;
import com.naosim.dddwork.domain.WorkRegulationException;
import com.naosim.dddwork.domain.YearMonth;
import com.naosim.dddwork.service.AttendanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;

@Controller
@RequiredArgsConstructor
public class AttendanceController {

    private final AttendanceArgumentParser argumentParser;
    private final AttendanceService attendanceService;

    public String command(String[] args) {
        try {
            if (args.length < 1) return "引数が足りません";

            String methodType = args[0];

            if ("input".equals(methodType)) {
                if (args.length < 4) return "引数が足りません";

                String date = args[1];
                String start = args[2];
                String end = args[3];
                input(date, start, end);
                return "";
            }

            if ("total".equals(methodType)) {
                if (args.length < 2) return "引数が足りません";

                String yearMonth = args[1];
                return total(yearMonth);
            }
        } catch (IllegalArgumentException e) {
            return "引数が不正です";
        } catch (WorkRegulationException e) {
            return e.getMessage();
        }

        return "methodTypeが不正です";
    }

    private void input(String dateStr, String startStr, String endStr) {
        LocalDate date = argumentParser.parseToLocalDate(dateStr);
        TimePoint startTime = argumentParser.parseToTimePoint(startStr);
        TimePoint endTime = argumentParser.parseToTimePoint(endStr);
        attendanceService.saveAttendance(date, startTime, endTime);
    }

    private String total(String yearMonthStr) {
        YearMonth yearMonth = argumentParser.parseToYearMonth(yearMonthStr);
        AttendanceSummary summary = attendanceService.fetchAttendanceSummary(yearMonth);
        return "勤務時間: " + toDisplayString(summary.getTotalWorkMinute()) + "\n"
                + "残業時間: " + toDisplayString(summary.getTotalOverWorkMinute());
    }

    private String toDisplayString(WorkMinute minute) {
        return minute.getValue() / 60 + "時間" + minute.getValue() % 60 + "分";
    }
}
