package com.naosim.dddwork.service;

import com.naosim.dddwork.domain.AttendanceRepository;
import com.naosim.dddwork.domain.use_case.TotalWorkingHoursApplication;
import com.naosim.dddwork.domain.use_case.TotalWorkingHoursResult;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
public class TotalWorkingHoursService {
    @Autowired
    AttendanceRepository attendanceRepository;

    public TotalWorkingHoursResult refer(TotalWorkingHoursApplication totalWorkingHoursApplication) {

        return attendanceRepository.get(
                    totalWorkingHoursApplication
                ).makeTotalWorkingHoursResult();
    }
}
