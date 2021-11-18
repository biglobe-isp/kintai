package api;

import api.Form.WorkDayForm;
import api.Form.WorkEndTimeForm;
import api.Form.WorkStartTimeForm;
import domain.WorkDay;
import domain.WorkEndTime;
import domain.WorkStartTime;

public class KintaiRegisterRequest {

    private WorkDayForm workDayForm;
    private WorkStartTimeForm workStartTimeForm;
    private WorkEndTimeForm workEndTimeForm;

    public KintaiRegisterRequest(String[] args) {
        this.workDayForm = new WorkDayForm(args[1]);
        this.workStartTimeForm = new WorkStartTimeForm(args[2]);
        this.workEndTimeForm = new WorkEndTimeForm(args[3]);
    }

    public WorkDay getWorkDay() {
        workDayForm.validationCheck();
        return new WorkDay(Integer.valueOf(workDayForm.value));
    }

    public WorkStartTime getWorkStartTime() {
        workStartTimeForm.validationCheck();
        return new WorkStartTime(Integer.valueOf(workStartTimeForm.value));
    }

    public WorkEndTime getWorkEndTime() {
        workEndTimeForm.validationCheck();
        return new WorkEndTime(Integer.valueOf(workStartTimeForm.value));
    }

}
