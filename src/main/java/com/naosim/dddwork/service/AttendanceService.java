package com.naosim.dddwork.service;

import com.naosim.dddwork.domain.Attendance;
import com.naosim.dddwork.domain.AttendanceFactory;
import com.naosim.dddwork.domain.AttendanceRepository;
import com.naosim.dddwork.domain.AttendanceSummary;
import com.naosim.dddwork.domain.AttendanceSummaryFactory;
import com.naosim.dddwork.domain.TimePoint;
import com.naosim.dddwork.domain.WorkTimeOfDay;
import com.naosim.dddwork.domain.WorkTimeOfMonth;
import com.naosim.dddwork.domain.YearMonth;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AttendanceService {

    private final AttendanceRepository attendanceRepository;
    private final AttendanceFactory attendanceFactory;
    private final AttendanceSummaryFactory attendanceSummaryFactory;
    private final WorkMinuteCalculator workMinuteCalculator;
    private final Clock clock;

    public void saveAttendance(LocalDate date, TimePoint startTime, TimePoint endTime) {
        WorkTimeOfDay workTimeOfDay = workMinuteCalculator.calculateOfDay(startTime, endTime);
        Attendance attendance = attendanceFactory.create(
                date,
                startTime,
                endTime,
                workTimeOfDay.getWorkMinute(),
                workTimeOfDay.getOverWorkMinute(),
                LocalDate.now(clock)
        );
        attendanceRepository.save(attendance);
    }

    public AttendanceSummary fetchAttendanceSummary(YearMonth yearMonth) {
        List<WorkTimeOfDay> workTimeOfDays = attendanceRepository.fetchMonthly(yearMonth)
                .stream()
                .map((x) -> (WorkTimeOfDay) x)
                .collect(Collectors.toList());
        WorkTimeOfMonth workTimeOfMonth = workMinuteCalculator.calculateOfMonth(workTimeOfDays);
        return attendanceSummaryFactory.create(yearMonth, workTimeOfMonth);
    }
}
