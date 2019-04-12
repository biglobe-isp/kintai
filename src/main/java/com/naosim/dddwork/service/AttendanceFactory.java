package com.naosim.dddwork.service;

import com.naosim.dddwork.domain.Attendance;
import com.naosim.dddwork.domain.TimePoint;
import com.naosim.dddwork.domain.WorkRegulationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.time.LocalDate;

@Component
class AttendanceFactory {

    private final WorkRegulationRepository workRegulationRepository;

    @Autowired
    public AttendanceFactory(WorkRegulationRepository workRegulationRepository) {
        this.workRegulationRepository = workRegulationRepository;
    }

    Attendance create(LocalDate date, TimePoint startTime, TimePoint endTime, LocalDate createDate) {
        // TODO: Not Implemented
        throw new NotImplementedException();
    }
}
