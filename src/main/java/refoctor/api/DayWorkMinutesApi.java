package refoctor.api;

import refoctor.datasource.DayWorkMinutesDb;
import refoctor.domain.japan.DateDomain;
import refoctor.domain.japan.EndTime;
import refoctor.domain.japan.StartTime;
import refoctor.domain.japan.WorkTime;
import refoctor.service.DayWorkMinutesService;

public class DayWorkMinutesApi {
    public static void inputApi(DateDomain dateDomain, StartTime startTime, EndTime endTime, WorkTime workTime, DayWorkMinutesDb dayWorkMinutesDb) {

        DayWorkMinutesService dayWorkMinutesService = new DayWorkMinutesService();

        dayWorkMinutesService.workService(dateDomain, startTime,endTime, workTime, dayWorkMinutesDb);

    }
}
