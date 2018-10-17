package refoctor.api;

import refoctor.datasource.TotalWorkMinutesDb;
import refoctor.domain.ArgsList;
import refoctor.service.TotalWorkMinutesService;

public class TotalWorkMinutesApi {
    public static void totalApi(ArgsList argsList) {
        TotalWorkMinutesService totalWorkMinutesService = new TotalWorkMinutesService();
        TotalWorkMinutesDb totalWorkMinutesDb = new TotalWorkMinutesDb();

        totalWorkMinutesService.workService(argsList, totalWorkMinutesDb);
    }
}
