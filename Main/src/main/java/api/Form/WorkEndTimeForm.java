package api.Form;

import com.sun.istack.internal.NotNull;

public class WorkEndTimeForm {
    public String value;

    public WorkEndTimeForm(String value) {
        this.value = value;
    }

    @NotNull
    public void validationCheck() {
        if (value.length() != 4) {
            throw new RuntimeException("例外が発生");
        }
    }
}
