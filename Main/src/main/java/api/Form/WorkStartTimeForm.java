package api.Form;

import domain.WorkStartTime;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString(includeFieldNames = false)
@EqualsAndHashCode
@AllArgsConstructor
public class WorkStartTimeForm {
    private String value;

    public WorkStartTime getValueObject(String value) {
        if (value.length() != 4) throw new RuntimeException("例外が発生");
        int startTime = Integer.valueOf(value);
        int startH = Integer.valueOf(value.substring(0, 2));
        int startM = Integer.valueOf(value.substring(2, 4));
        WorkStartTime workStartTime = new WorkStartTime(startTime, startH, startM);
        return workStartTime;
    }
}