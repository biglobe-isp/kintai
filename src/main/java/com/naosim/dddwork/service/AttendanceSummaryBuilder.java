package com.naosim.dddwork.service;

import com.naosim.dddwork.domain.Attendance;
import com.naosim.dddwork.domain.AttendanceSummary;
import com.naosim.dddwork.domain.YearMonth;
import org.springframework.stereotype.Component;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;

@Component
class AttendanceSummaryBuilder {

    AttendanceSummary build(YearMonth yearMonth, List<Attendance> attendances) {
        // TODO: Not Implemented
        throw new NotImplementedException();
    }
}
