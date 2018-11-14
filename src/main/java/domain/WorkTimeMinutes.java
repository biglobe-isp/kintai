package domain;

public class WorkTimeMinutes {

    public int workTimeMinutes;

    public WorkTimeMinutes(StartTime startTime, EndTime endTime) {

        StartTimeHour startTimeHour = new StartTimeHour(startTime);
        StartTimeMinute startTimeMinute = new StartTimeMinute(startTime);
        EndTimeHour endTimeHour = new EndTimeHour(endTime);
        EndTimeMinute endTimeMinute = new EndTimeMinute(endTime);

        this.workTimeMinutes = endTimeHour.endTimeHour * 60
                + endTimeMinute.endTimeMinute
                - (startTimeHour.startTimeHour * 60 + startTimeMinute.startTimeMinute);
        // @todo メッソドを分ける
        if (endTimeHour.endTimeHour == 12) {
            this.workTimeMinutes -= endTimeMinute.endTimeMinute;
        } else if (endTimeHour.endTimeHour >= 13) {
            this.workTimeMinutes -= 60;
        }

        if (endTimeHour.endTimeHour == 18) {
            this.workTimeMinutes -= endTimeMinute.endTimeMinute;
        } else if (endTimeHour.endTimeHour >= 19) {
            this.workTimeMinutes -= 60;
        }

        if (endTimeHour.endTimeHour == 21) {
            this.workTimeMinutes -= endTimeMinute.endTimeMinute;
        } else if (endTimeHour.endTimeHour >= 22) {
            this.workTimeMinutes -= 60;
        }
    }

}
