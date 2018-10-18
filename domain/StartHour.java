package domain;

public class StartHour {
    private final int startHour;

    StartHour(String startDate){
        this.startHour = Integer.valueOf(startDate.substring(0, 2));
    }

    public int getStartHourInt() {
        return startHour;
    }

    public String getStartHourStr() {
        return String.valueOf(startHour);
    }

}