package com.naosim.dddwork.kintai.service;

import com.naosim.dddwork.kintai.domain.timerecord.attendance.AttendanceDate;
import com.naosim.dddwork.kintai.domain.timerecord.regulation.RegulatedBreakTimeShift;
import com.naosim.dddwork.kintai.domain.timerecord.regulation.RegulatedWorkingTimeMinutes;

public interface RegulationRepository {
    public RegulatedBreakTimeShift fetchBreakTimeShift(AttendanceDate attendanceDate);
    public RegulatedWorkingTimeMinutes fetchRegulatedWorkingTimeMinutes(AttendanceDate attendanceDate);
}
