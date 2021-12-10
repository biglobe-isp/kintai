package api.Form;

import lombok.Getter;

public class TotallyMonthForm {
    @Getter
    private String value;

    public TotallyMonthForm(String value) {
        this.value = value;
    }
}
