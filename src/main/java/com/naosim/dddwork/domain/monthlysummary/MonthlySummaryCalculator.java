package com.naosim.dddwork.domain.monthlysummary;

import com.naosim.dddwork.domain.AttendanceRepository;
import com.naosim.dddwork.domain.TimeUnit;
import com.naosim.dddwork.domain.attendance.Attendance;
import com.naosim.dddwork.domain.service.MonthlySummaryCalculable;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class MonthlySummaryCalculator implements MonthlySummaryCalculable {

    private final AttendanceRepository attendanceRepository;

    @Override
    public MonthlySummary aggregateSpecifiedMonthAttendance(YearMonth yearMonth) {
        List<Attendance> list = attendanceRepository.findSpecifiedYearMonth(yearMonth);
        int totalWorkingHours = 0;
        int totalOverTimeHours = 0;

        Map<String, Integer> totalWorkMinutesMap = new HashMap<>();
        Map<String, Integer> totalOverTimeMinutesMap = new HashMap<>();

        for(Attendance attendance : list) {
            totalWorkMinutesMap.put(attendance.getWorkDay().toString(),
                                    attendance.getWorkingHours().getTimeUnit().getTotalMinutes());
            totalOverTimeMinutesMap.put(attendance.getWorkDay().toString(),
                                        attendance.getOverTimeHours().getTimeUnit().getTotalMinutes());
        }

        Set<String> keySet = totalWorkMinutesMap.keySet();
        for(String key : keySet) {
            totalWorkingHours += totalWorkMinutesMap.get(key);
            totalOverTimeHours += totalOverTimeMinutesMap.get(key);
        }

        return MonthlySummary.of(yearMonth, TimeUnit.of(totalWorkingHours), TimeUnit.of(totalOverTimeHours));
    }
}
