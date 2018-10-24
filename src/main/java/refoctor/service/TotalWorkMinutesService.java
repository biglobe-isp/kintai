package refoctor.service;

import refoctor.domain.japan.DateDomain;
import refoctor.domain.japan.TotalWorkMinutesCalc;
import refoctor.domain.japan.TotalWorkMinutesRepository;

public class TotalWorkMinutesService {
    public void workService(DateDomain dateDomain, TotalWorkMinutesRepository totalWorkMinutesRepository) {

        TotalWorkMinutesCalc totalWorkMinutesCalc = new TotalWorkMinutesCalc();

        totalWorkMinutesCalc.total(dateDomain, totalWorkMinutesRepository);
    }
}
