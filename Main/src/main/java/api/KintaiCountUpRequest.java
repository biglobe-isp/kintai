package api;

import api.Form.TotallyMonthForm;
import com.sun.istack.internal.NotNull;
import domain.TotallyMonth;
import jdk.nashorn.internal.objects.annotations.Getter;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString(includeFieldNames = false)
@EqualsAndHashCode
public class KintaiCountUpRequest {

    @NotNull
    private TotallyMonthForm totallyMonthForm;
    private String value;

    public KintaiCountUpRequest(String[] args) {
        this.value = args[1];
    }

    @Getter
    public TotallyMonth getValueObject() {
        return totallyMonthForm.getValueObject(value);
    }

}
