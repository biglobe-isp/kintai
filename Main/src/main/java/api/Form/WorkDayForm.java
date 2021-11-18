package api.Form;

public class WorkDayForm {
    public String value;

    public WorkDayForm(String value) {
        this.value = value;
    }

    public void validationCheck() {
        if (value.length() != 8) throw new RuntimeException("例外が発生");
    }
}
