package com.naosim.dddwork.domain;

class WorkTimeMoments {
    private final TimeMoment startWorkTime;
    private final TimeMoment endWorkTime;
    private final PrescribedWorkHours prescribedWorkHours = new PrescribedWorkHours();

    WorkTimeMoments(TimeMoment startWorkTime, TimeMoment endWorkTime) {
        this.startWorkTime = new TimeMoment(startWorkTime);
        // if(ValidateStartWorkTime()) System.err.println("勤務開始時間が不正な値です。");

        this.endWorkTime = new TimeMoment(endWorkTime);
        if(ValidateEndWorkTime()) System.err.println("勤務終了時間が不正な値です。");
    }

    private boolean ValidateStartWorkTime() {
        return new MinutesDifference(startWorkTime, prescribedWorkHours.GetPrescribeStartWorkTime()).ValidateMinutesDifference();
    }

    private boolean ValidateEndWorkTime() {
        return new MinutesDifference(endWorkTime, endWorkTime).ValidateMinutesDifference();
    }

    TimeMoment getStartWorkTime() {
        return new TimeMoment(startWorkTime);
    }

    TimeMoment getEndWorkTime() {
        return new TimeMoment(endWorkTime);
    }
}