package com.naosim.dddwork.service;

import com.naosim.dddwork.domain.AttendanceRepository;
import com.naosim.dddwork.domain.attendance.Attendance;
import com.naosim.dddwork.domain.service.MonthlySummaryCalculable;
import com.naosim.dddwork.domain.monthlysummary.MonthlySummary;
import com.naosim.dddwork.domain.monthlysummary.YearMonth;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MonthlySummaryService {

    private final AttendanceRepository attendanceRepository;
    private final MonthlySummaryCalculable monthlySummaryCalculator;

    public MonthlySummary acquireMonthlyTotal(YearMonth yearMonth) {
        List<Attendance> attendanceList = attendanceRepository.findSpecifiedYearMonth(yearMonth);
        return monthlySummaryCalculator.aggregateSpecifiedMonthAttendance(attendanceList);
    }
}
