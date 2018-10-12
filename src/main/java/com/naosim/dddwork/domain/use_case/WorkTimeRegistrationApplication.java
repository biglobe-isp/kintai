package com.naosim.dddwork.domain.use_case;

import com.naosim.dddwork.domain.DailyAttendance;
import com.naosim.dddwork.domain.LaborRegulations;
import com.naosim.dddwork.domain.ClosingHours;
import com.naosim.dddwork.domain.OvertimeHours;
import com.naosim.dddwork.domain.StartingHours;
import com.naosim.dddwork.domain.WorkDay;
import com.naosim.dddwork.domain.WorkingHours;
import com.naosim.dddwork.domain.WorkingTime;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@AllArgsConstructor
@EqualsAndHashCode
public class WorkTimeRegistrationApplication {
    @Getter
    private  final WorkDay workDay;
    @Getter
    private  final StartingHours startingHours;
    @Getter
    private  final ClosingHours closingHours;

    public DailyAttendance makeDailyAttendance(LaborRegulations laborRegulations) {
        return new DailyAttendance(
                this.workDay,
                this.startingHours,
                this.closingHours,
                getWorkingHours(laborRegulations),
                getOvertimeHours(getWorkingHours(laborRegulations), laborRegulations.getFixedTime().getWorkingTime())
        );
    }

    private WorkingHours getWorkingHours(LaborRegulations laborRegulations){

        return new WorkingHours(
                (this.closingHours.getClosingHoursMinutes().getValue() - this.startingHours.getStartingHoursMinutes().getValue()) -
                (laborRegulations.getBreakTimeList().getValue().stream()
                        .filter(
                                BreakTime -> (BreakTime.getBreakEndTime().getValue().isBefore(this.closingHours.getValue()) &&
                                             BreakTime.getBreakStartTime().getValue().isBefore(this.closingHours.getValue()))
                        )
                        .mapToInt(
                                BreakTime -> BreakTime.getBreakEndTime().getBreakEndTimeMinutes().getValue()
                                        - BreakTime.getBreakStartTime().getBreakStartTimeMinutes().getValue()
                        ).sum()
                )
        );
    }

    private OvertimeHours getOvertimeHours(WorkingHours workingHours, WorkingTime workingTime){
        if (workingHours.getValue() >= workingTime.getValue() ){
            return new OvertimeHours(workingHours.getValue() - workingTime.getValue());
        }
        return new OvertimeHours(0);
    }
}
