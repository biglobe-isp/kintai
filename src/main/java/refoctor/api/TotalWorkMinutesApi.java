package refoctor.api;

import refoctor.service.TotalWorkMinutesService;

public class TotalWorkMinutesApi {
    public static void totalApi(String yearMonth) {

        TotalWorkMinutesService.totalService(yearMonth);
    }
}
