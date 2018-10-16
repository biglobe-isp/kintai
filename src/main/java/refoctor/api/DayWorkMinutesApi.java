package refoctor.api;

import refoctor.service.DayWorkMinutesService;

public class DayWorkMinutesApi {
    public static void inputApi(String date, String start, String end, String now) {

        DayWorkMinutesService.inputService(date, start, end, now);

    }
}
