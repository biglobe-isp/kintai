package refactor.domain;

public class WorkMinutes {
    public int calculateWorkMinutes(StartTime startTime, EndTime endTime) {
        int startHour = startTime.getStartHour();
        int startMinute = startTime.getStartMinutes();
        int endHour = endTime.getEndHour();
        int endMinutes = endTime.getEndMinutes();

        int workMinutes = endHour * 60 + endMinutes - (startHour * 60 + startMinute);
        if (endHour == 12) {
            workMinutes -= endMinutes;
        } else if (endHour >= 13) {
            workMinutes -= 60;
        }
        if (endHour == 18) {
            workMinutes -= endMinutes;
        } else if (endHour >= 19) {
            workMinutes -= 60;
        }

        if (endHour == 21) {
            workMinutes -= endMinutes;
        } else if (endHour >= 22) {
            workMinutes -= 60;
        }
        return workMinutes;
    }
}
