package api;

import api.Form.TotallyMonthForm;
import domain.TotallyMonth;
import jdk.nashorn.internal.objects.annotations.Getter;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString(includeFieldNames = false)
@EqualsAndHashCode
public class KintaiCountUpRequest {
    private TotallyMonthForm totallyMonthForm;

    public KintaiCountUpRequest(String[] args) {
        this.totallyMonthForm = new TotallyMonthForm(args[1]);
    }

    @Getter
    public TotallyMonth getValueObject() {
        totallyMonthForm.validationCheck();
        return new TotallyMonth(totallyMonthForm.value);
    }

}
