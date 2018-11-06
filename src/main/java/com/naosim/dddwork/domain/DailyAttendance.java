package com.naosim.dddwork.domain;

import com.naosim.dddwork.domain.use_case.WorkTimeRegistrationApplication;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@ToString(includeFieldNames = false)
@EqualsAndHashCode
@RequiredArgsConstructor
public class DailyAttendance {
    @Getter
    private final WorkDay workDay;
    @Getter
    private final StartingHours startingHours;
    @Getter
    private final ClosingHours closingHours;
    @Getter
    private final WorkingHours workingHours;
    @Getter
    private final OvertimeHours overtimeHours;

    public DailyAttendance(WorkTimeRegistrationApplication workTimeRegistrationApplication, LaborRegulations laborRegulations) {
        this.workDay = workTimeRegistrationApplication.getWorkDay();
        this.startingHours = workTimeRegistrationApplication.getStartingHours();
        this.closingHours = workTimeRegistrationApplication.getClosingHours();
        this.workingHours = getWorkingHours(laborRegulations);
        this.overtimeHours = getOvertimeHours(this.workingHours, laborRegulations.getFixedTime().getWorkingTime());
    }

    private WorkingHours getWorkingHours(LaborRegulations laborRegulations) {

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

    private OvertimeHours getOvertimeHours(WorkingHours workingHours, WorkingTime workingTime) {
        if (workingHours.getValue() >= workingTime.getValue()) {
            return new OvertimeHours(workingHours.getValue() - workingTime.getValue());
        }
        return new OvertimeHours(0);
    }

}
