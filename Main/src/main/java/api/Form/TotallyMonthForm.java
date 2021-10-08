package api.Form;

import domain.TotallyMonth;

public class TotallyMonthForm {
    private String value;

    public TotallyMonth getValueObject(String value){
        if (!value.equals("total")) throw new RuntimeException("例外が発生");
        return new TotallyMonth(value);
    }
}
