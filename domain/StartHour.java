package domain;

public class StartHour {
    private final int startHour;

    StartHour(String startDate){
        this.startHour = Integer.valueOf(startDate.substring(0, 2));
    }

    public int getStartHour() {
        return startHour;
    }
}