package domain;

public class EndHour {
    private final int endHour;

    EndHour(String endDate){
        this.endHour = Integer.valueOf(endDate.substring(0, 2));
    }

    public int getEndHour() {
        return endHour;
    }
}