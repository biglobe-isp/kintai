package refoctor.api;

import refoctor.service.DayWorkMinutesService;

public class DayWorkMinutesApi {
    public static void inputApi(String[] args) {

        DayWorkMinutesService dayWorkMinutesService = new DayWorkMinutesService();
        dayWorkMinutesService.workService(args);

    }
}
