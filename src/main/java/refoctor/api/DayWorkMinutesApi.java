package refoctor.api;

import refoctor.datasource.DayWorkMinutesDb;
import refoctor.domain.ArgsList;
import refoctor.service.DayWorkMinutesService;

public class DayWorkMinutesApi {
    public static void inputApi(ArgsList argsList) {

        DayWorkMinutesService dayWorkMinutesService = new DayWorkMinutesService();
        DayWorkMinutesDb dayWorkMinutesDb = new DayWorkMinutesDb();

        dayWorkMinutesService.workService(argsList, dayWorkMinutesDb);

    }
}
