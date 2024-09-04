package com.naosim.dddwork.domain;

class PrescribedWorkHours {
    private final TimeMoment PRESCRIBE_START_WORK_TIME = new TimeMoment(9, 0);
    private final TimeMoment PRESCRIBE_END_WORK_TIME = new TimeMoment(18, 0);
    private final MinutesDifference timeDifference = new MinutesDifference(PRESCRIBE_START_WORK_TIME, PRESCRIBE_END_WORK_TIME);

    PrescribedWorkHours(){
    }

    TimeMoment GetPrescribeStartWorkTime() {
        return new TimeMoment(PRESCRIBE_START_WORK_TIME);
    }
}