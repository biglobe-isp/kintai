package refoctor.domain;

import java.time.LocalDateTime;

public class DayWorkMinutesCalc {
    private String date;
    private String start;
    private String end;
    private String now = LocalDateTime.now().toString();
    private int    startH;
    private int    startM;
    private int    endH;
    private int    endM;
    private int    workMinutes;
    private int    overWorkMinutes;

    public void input(DayWorkMinutesRepository dayWorkMinutesRepository , ArgsList argsList) {

        date = argsList.getDate();
        start = argsList.getStart();
        end = argsList.getEnd();
        now = LocalDateTime.now().toString();

        startH = Integer.valueOf(start.substring(0, 2));
        startM = Integer.valueOf(start.substring(2, 4));

        endH = Integer.valueOf(end.substring(0, 2));
        endM = Integer.valueOf(end.substring(2, 4));
        workMinutes = endH * 60 + endM - (startH * 60 + startM);

        if (endH == 12) {
            workMinutes -= endM;
        } else if (endH >= 13) {
            workMinutes -= 60;
        }

        if (endH == 18) {
            workMinutes -= endM;
        } else if (endH >= 19) {
            workMinutes -= 60;
        }

        if (endH == 21) {
            workMinutes -= endM;
        } else if (endH >= 22) {
            workMinutes -= 60;
        }

        overWorkMinutes = Math.max(workMinutes - 8 * 60, 0);

        dayWorkMinutesRepository.dayOutPut(date, start, end, workMinutes, overWorkMinutes, now);

    }
}
