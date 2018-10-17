package refoctor.service;

import refoctor.domain.ArgsList;
import refoctor.domain.TotalWorkMinutesCalc;
import refoctor.domain.TotalWorkMinutesRepository;

public class TotalWorkMinutesService {
    public void workService(ArgsList argsList, TotalWorkMinutesRepository totalWorkMinutesRepository) {
        TotalWorkMinutesCalc totalWorkMinutesCalc = new TotalWorkMinutesCalc();
        totalWorkMinutesCalc.total(totalWorkMinutesRepository, argsList);
    }
}
