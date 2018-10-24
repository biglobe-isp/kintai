package refoctor.api;

import refoctor.datasource.DayWorkMinutesDb;

public class Main {
    public static void main(String[] args) {
        DayWorkMinutesDb dayWorkMinutesDb = new DayWorkMinutesDb();
        CheckForm checkForm = new CheckForm(args);

        if(MethodType.input.equals(checkForm.getMethodType())){

            DayWorkMinutesApi.inputApi(checkForm.getDateDomain(), checkForm.getStartTime(), checkForm.getEndTime(), checkForm.getWorkTime(), dayWorkMinutesDb);

        }else if (MethodType.total.equals(checkForm.getMethodType())){

            TotalWorkMinutesApi.totalApi(checkForm.getDateDomain());

        }
    }
}