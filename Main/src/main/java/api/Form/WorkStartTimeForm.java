package api.Form;

public class WorkStartTimeForm {
    public String value;

    public WorkStartTimeForm(String value) {
        this.value = value;
    }

    public void validationCheck() {
        if (value.length() != 4) throw new RuntimeException("例外が発生");
    }
}