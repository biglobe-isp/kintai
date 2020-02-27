package com.naosim.dddwork.domain.attendance;

import com.google.common.base.Strings;
import com.naosim.dddwork.domain.IAttendanceFactory;
import com.naosim.dddwork.domain.IOverTimeHoursCalculator;
import com.naosim.dddwork.domain.IWorkingHoursCalculator;
import com.naosim.dddwork.domain.TimePoint;
import com.naosim.dddwork.domain.TimeUnit;
import com.naosim.dddwork.domain.WorkRegulationsRepository;
import com.naosim.dddwork.domain.workregulations.WorkRegulations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class AttendanceFactory implements IAttendanceFactory {

    private IWorkingHoursCalculator iWorkingHoursCalculator;
    private IOverTimeHoursCalculator iOverTimeHoursCalculator;
    private WorkRegulationsRepository workRegulationsRepository;

    @Autowired
    public AttendanceFactory(IWorkingHoursCalculator iWorkingHoursCalculator,
                             IOverTimeHoursCalculator iOverTimeHoursCalculator,
                             WorkRegulationsRepository workRegulationsRepository) {
        this.iWorkingHoursCalculator = iWorkingHoursCalculator;
        this.iOverTimeHoursCalculator = iOverTimeHoursCalculator;
        this.workRegulationsRepository = workRegulationsRepository;
    }

    private Attendance create(LocalDate workDay, AttendanceTime attendanceTime, String workingH, String overTimeH) {
        WorkRegulations workRegulations = workRegulationsRepository.getCurrentRegulations();
        TimeUnit workingHours;

        if(Strings.isNullOrEmpty(workingH)) {
            workingHours = iWorkingHoursCalculator.calcWorkingHours(attendanceTime, workRegulations);
        } else {
            workingHours = TimeUnit.of(Integer.parseInt(workingH));
        }

        TimeUnit overTimeHours;
        if(Strings.isNullOrEmpty(workingH)) {
            overTimeHours = iOverTimeHoursCalculator.calcOverTimeHours(workingHours, workRegulations);
        } else {
            overTimeHours = TimeUnit.of(Integer.parseInt(overTimeH));
        }

        return new Attendance(workDay, attendanceTime, workingHours, overTimeHours);
    }

    @Override
    public Attendance createForRegister(LocalDate workDay, AttendanceTime attendanceTime) {
        return create(workDay, attendanceTime, null, null);
    }

    @Override
    public Attendance createFromCsv(String workD, String start, String end, String workingH, String overTimeH) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate workDay = LocalDate.parse(workD, formatter);

        AttendanceTime attendanceTime =
                AttendanceTime.of(StartTime.of(TimePoint.of(start)), EndTime.of(TimePoint.of(end)));

        return create(workDay, attendanceTime, workingH, overTimeH);
    }
}
