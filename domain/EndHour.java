package domain;

public class EndHour {
    private final int endHour;

    EndHour(String endDate){
        this.endHour = Integer.valueOf(endDate.substring(0, 2));
    }

    public int getEndHourInt() {
        return endHour;
    }

    public String getEndHourStr() {
        return String.valueOf(endHour);
    }
}