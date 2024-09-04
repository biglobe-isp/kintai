package com.naosim.dddwork.domain;

class WorkTimeMoments {
    private final TimeMoment startWorkTime;
    private final TimeMoment endWorkTime;
    private final PrescribedWorkHours prescribedWorkHours = new PrescribedWorkHours();

    WorkTimeMoments(int startHour, int startMinute, int endHour, int endMinute) {
        startWorkTime = new TimeMoment(startHour, startMinute);
        if(ValidateStartWorkTime()) System.err.println("勤務開始時間が不正な値です。");

        endWorkTime = new TimeMoment(endHour, endMinute);
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