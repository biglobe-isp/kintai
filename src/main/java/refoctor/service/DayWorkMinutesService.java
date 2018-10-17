package refoctor.service;

import refoctor.domain.ArgsList;
import refoctor.domain.DayWorkMinutesCalc;
import refoctor.domain.DayWorkMinutesRepository;

public class DayWorkMinutesService {
    public void workService(ArgsList argsList, DayWorkMinutesRepository dayWorkMinutesRepository) {
        DayWorkMinutesCalc dayWorkMinutesCalc = new DayWorkMinutesCalc();
        dayWorkMinutesCalc.input(dayWorkMinutesRepository, argsList);
    }

}
