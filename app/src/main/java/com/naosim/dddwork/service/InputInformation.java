package com.naosim.dddwork.service;

import com.naosim.dddwork.domain.daily_work.EndWorkTime;
import com.naosim.dddwork.domain.daily_work.StartWorkTime;
import com.naosim.dddwork.domain.daily_work.WorkDate;

import java.util.Optional;

/**
 * 入力情報
 */
public class InputInformation {
    private ExecutiveCommandType executiveCommandType;
    private Optional<WorkDate> workDate;
    private Optional<StartWorkTime> startWorkTime;
    private Optional<EndWorkTime> endWorkTime;

    public void setExecutiveCommandType(ExecutiveCommandType executiveCommandType) {
        this.executiveCommandType = executiveCommandType;
    }

    public void setWorkDate(WorkDate workDate) {
        this.workDate = Optional.of(workDate);
    }

    public void setStartWorkTime(StartWorkTime startWorkTime) {
        this.startWorkTime = Optional.of(startWorkTime);
    }

    public void setEndWorkTime(EndWorkTime endWorkTime) {
        this.endWorkTime = Optional.of(endWorkTime);
    }

    public ExecutiveCommandType getExecutiveCommandType() {
        return executiveCommandType;
    }

    public WorkDate getWorkDate() {
        return workDate.orElse(null);
    }

    public StartWorkTime getStartWorkTime() {
        return startWorkTime.orElse(null);
    }

    public EndWorkTime getEndWorkTime() {
        return endWorkTime.orElse(null);
    }
}