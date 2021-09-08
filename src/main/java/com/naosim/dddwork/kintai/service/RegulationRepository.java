package com.naosim.dddwork.kintai.service;

import com.naosim.dddwork.kintai.domain.timerecord.attendance.AttendanceDate;
import com.naosim.dddwork.kintai.domain.timerecord.regulation.RegulatedBreakTimeShift;
import com.naosim.dddwork.kintai.domain.timerecord.regulation.RegulatedWorkingTimeInterval;
import com.naosim.dddwork.kintai.domain.timerecord.regulation.RegulatedWorkingTimeMinutes;

public interface RegulationRepository {
    RegulatedBreakTimeShift fetchBreakTimeShift(AttendanceDate attendanceDate) throws Exception;
    RegulatedWorkingTimeMinutes fetchRegulatedWorkingTimeMinutes(AttendanceDate attendanceDate) throws Exception;
    RegulatedWorkingTimeInterval fetchRegulatedWorkingTimeInterval(AttendanceDate attendanceDate)  throws Exception;
}
