package api.Form;

import com.sun.istack.internal.NotNull;
import domain.WorkDay;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString(includeFieldNames = false)
@EqualsAndHashCode
@AllArgsConstructor
public class WorkDayForm {
    private String value;

    @NotNull
    public WorkDay getValueObject(String value) {
        if (value.length() != 8) throw new RuntimeException("例外が発生");
        int day = Integer.valueOf(value);
        return new WorkDay(day);
    }
}
