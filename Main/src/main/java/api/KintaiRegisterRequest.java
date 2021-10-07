package api;

import api.Form.WorkDayForm;
import api.Form.WorkEndTimeForm;
import api.Form.WorkStartTimeForm;
import com.sun.istack.internal.NotNull;
import domain.WorkDay;
import domain.WorkEndTime;
import domain.WorkStartTime;
import jdk.nashorn.internal.objects.annotations.Getter;

public class KintaiRegisterRequest {
    private String date;
    private String start;
    private String end;

    @NotNull
    private WorkDayForm workDayForm;
    @NotNull
    private WorkStartTimeForm workStartTimeForm;
    @NotNull
    private WorkEndTimeForm workEndTimeForm;

    public KintaiRegisterRequest(String[] args) {
        String date = args[1];
        String start = args[2];
        String end = args[3];
    }

    @Getter
    public WorkDay getWorkDay() {
        return workDayForm.check(date);
    }

    @Getter
    public WorkStartTime getWorkStartTime() {
        return workStartTimeForm.check(start);
    }

    @Getter
    public WorkEndTime getWorkEndTime() {
        return workEndTimeForm.check(end);
    }

}
