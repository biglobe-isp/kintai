package refoctor.api;

import refoctor.datasource.TotalWorkMinutesDb;
import refoctor.domain.japan.DateDomain;
import refoctor.service.TotalWorkMinutesService;

public class TotalWorkMinutesApi {
    public static void totalApi(DateDomain dateDomain) {

        TotalWorkMinutesService totalWorkMinutesService = new TotalWorkMinutesService();

        TotalWorkMinutesDb totalWorkMinutesDb = new TotalWorkMinutesDb();

        totalWorkMinutesService.workService(dateDomain, totalWorkMinutesDb);
    }
}
