package com.naosim.dddwork.domain.service;

import com.naosim.dddwork.domain.attendance.Attendance;
import com.naosim.dddwork.domain.monthlysummary.MonthlySummary;

import java.util.List;

public interface MonthlySummaryCalculable {
    MonthlySummary aggregateSpecifiedMonthAttendance(List<Attendance> attendanceList);
}
