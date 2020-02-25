package com.naosim.dddwork.domain.attendance;

import com.naosim.dddwork.domain.TimeRange;
import com.naosim.dddwork.domain.TimeUnit;
import lombok.Value;

@Value
public class WorkingHours {
     TimeUnit timeUnit;

     public static WorkingHours of(AttendanceTime attendanceTime, BreakTimeHours breakTimeHours) {
        return new WorkingHours(attendanceTime, breakTimeHours);
     }

     private WorkingHours(AttendanceTime attendanceTime, BreakTimeHours breakTimeHours) {

         TimeRange attendanceTimeRange = TimeRange.of(attendanceTime.getStartTime().getTimePoint(),
                                                      attendanceTime.getEndTime().getDeemedEndTime());

         int workingMinutes = attendanceTimeRange.getRangeMinutes() - breakTimeHours.getTimeUnit().getTotalMinutes();
         this.timeUnit = TimeUnit.of(workingMinutes);
     }
}
