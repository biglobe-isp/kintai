package api.Form;

import com.sun.istack.internal.NotNull;
import domain.WorkDay;


public class WorkDayForm {
    private String value;

    @NotNull
    public WorkDay check(String value) {
        if (value.length() != 8) throw new RuntimeException("例外が発生");
        int day = Integer.valueOf(value);
        WorkDay workDay = new WorkDay(day);
        return workDay;
    }
}
