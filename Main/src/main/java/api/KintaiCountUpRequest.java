package api;

import api.Form.TotallyMonthForm;
import com.sun.istack.internal.NotNull;
import jdk.nashorn.internal.objects.annotations.Getter;

public class KintaiCountUpRequest {
    @NotNull
    private TotallyMonthForm totallyMonthForm;
    private String value;

    public KintaiCountUpRequest(String[] args) {
        this.value = args[1];
    }

    @Getter
    public String getValueObject() {
        return totallyMonthForm.check(value);
    }

}
