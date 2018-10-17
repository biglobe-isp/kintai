package refoctor.api;

import refoctor.domain.ArgsList;
import refoctor.domain.MethodType;

public class Main {
    public static void main(String[] args) {
        ArgsList argsList = new ArgsList(args);
        argsList.checkLength();

        if(argsList.getMethodType().equals(MethodType.input)) {

            argsList.inputCheckLength();
            DayWorkMinutesApi.inputApi(argsList);

        } else if (argsList.getMethodType().equals(MethodType.total)) {

            argsList.totalCheckLength();
            TotalWorkMinutesApi.totalApi(argsList);

        }
    }
}