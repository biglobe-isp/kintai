package refoctor.service;

import refoctor.domain.japan.*;

public class DayWorkMinutesService {
    public void workService(DateDomain dateDomain, StartTime startTime, EndTime endTime, WorkTime workTime, DayWorkMinutesRepository dayWorkMinutesRepository) {

        DayWorkMinutesCalc dayWorkMinutesCalc = new DayWorkMinutesCalc();

        dayWorkMinutesCalc.input(dateDomain, startTime, endTime, workTime, dayWorkMinutesRepository);
    }

}
