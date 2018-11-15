package refactor.domain;

public class WorkMinutes {
    public int calculateWorkMinutes(StartTime startTime, EndTime endTime) {
        int startH = startTime.getStartH();
        int startM = startTime.getStartM();
        int endH = endTime.getEndH();
        int endM = endTime.getEndM();

        int workMinutes = endH * 60 + endM - (startH * 60 + startM);
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
        return workMinutes;
    }
}
