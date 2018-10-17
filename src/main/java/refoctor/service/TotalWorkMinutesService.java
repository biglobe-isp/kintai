package refoctor.service;

import refoctor.domain.TotalWorkMinutesCalc;

public class TotalWorkMinutesService {
    public void workService(String[] args) {
        TotalWorkMinutesCalc totalWorkMinutesCalc = new TotalWorkMinutesCalc();

        if ("total".equals(args[0])) {

            totalWorkMinutesCalc.total(args);

        } else {
            throw new RuntimeException("methodTypeが不正です");
        }
    }
}
