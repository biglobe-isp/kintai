package refoctor.api;

import refoctor.service.TotalWorkMinutesService;

public class TotalWorkMinutesApi {
    public static void totalApi(String[] args) {
        TotalWorkMinutesService totalWorkMinutesService = new TotalWorkMinutesService();
        totalWorkMinutesService.workService(args);
    }
}
