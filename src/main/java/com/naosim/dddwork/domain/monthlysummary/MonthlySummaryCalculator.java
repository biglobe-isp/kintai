package com.naosim.dddwork.domain.monthlysummary;

import com.naosim.dddwork.domain.TimeUnit;
import com.naosim.dddwork.domain.attendance.Attendance;
import com.naosim.dddwork.domain.service.MonthlySummaryCalculable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class MonthlySummaryCalculator implements MonthlySummaryCalculable {

    @Override
    public MonthlySummary aggregateSpecifiedMonthAttendance(List<Attendance> attendanceList) {
        int totalWorkingHours = 0;
        int totalOverTimeHours = 0;

        Map<String, Integer> totalWorkMinutesMap = new HashMap<>();
        Map<String, Integer> totalOverTimeMinutesMap = new HashMap<>();

        for(Attendance attendance : attendanceList) {
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

        return MonthlySummary.of(TimeUnit.of(totalWorkingHours), TimeUnit.of(totalOverTimeHours));
    }
}
