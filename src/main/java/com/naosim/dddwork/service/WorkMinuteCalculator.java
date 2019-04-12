package com.naosim.dddwork.service;

import com.naosim.dddwork.domain.TimePoint;
import com.naosim.dddwork.domain.WorkMinute;
import com.naosim.dddwork.domain.WorkRegulation;
import com.naosim.dddwork.domain.WorkRegulationRepository;
import com.naosim.dddwork.domain.WorkTimeOfDay;
import com.naosim.dddwork.domain.WorkTimeOfMonth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
class WorkMinuteCalculator {

    private final WorkRegulationRepository workRegulationRepository;

    @Autowired
    public WorkMinuteCalculator(WorkRegulationRepository workRegulationRepository) {
        this.workRegulationRepository = workRegulationRepository;
    }

    WorkTimeOfDay calculateOfDay(TimePoint startTime, TimePoint endTime) {
        WorkRegulation workRegulation = workRegulationRepository.fetchDefault();

        // TODO: Not Implemented
        return new WorkTimeOfDay() {

            @Override
            public WorkMinute getWorkMinute() {
                return null;
            }

            @Override
            public WorkMinute getOverWorkMinute() {
                return null;
            }
        };
    }

    WorkTimeOfMonth calculateOfMonth(List<WorkTimeOfDay> workTimeOfDays) {

        // TODO: Not Implemented
        return new WorkTimeOfMonth() {

            @Override
            public WorkMinute getTotalWorkMinute() {
                return null;
            }

            @Override
            public WorkMinute getTotalOverWorkMinute() {
                return null;
            }
        };
    }
}
