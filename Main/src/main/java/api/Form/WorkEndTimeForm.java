package api.Form;

import com.sun.istack.internal.NotNull;
import domain.WorkEndTime;

public class WorkEndTimeForm {
    private String value;

    @NotNull
    public WorkEndTime check(String value) {
        if (value.length() != 4) {throw new RuntimeException("例外が発生");}
        int endTime = Integer.valueOf(value);
        int endH = Integer.valueOf(value.substring(0, 2));
        int endM = Integer.valueOf(value.substring(2, 4));
        WorkEndTime workEndTime = new WorkEndTime(endTime,endH, endM);
        return workEndTime;
    }
}
