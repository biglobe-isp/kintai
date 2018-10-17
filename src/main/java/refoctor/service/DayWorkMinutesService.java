package refoctor.service;

import refoctor.domain.DayWorkMinutesCalc;

public class DayWorkMinutesService {
    public void workService(String[] args) {
        DayWorkMinutesCalc dayWorkMinutesCalc = new DayWorkMinutesCalc();

        if ("input".equals(args[0])) {

            dayWorkMinutesCalc.input(args);

        } else {
            throw new RuntimeException("methodTypeが不正です");
        }
    }

}
