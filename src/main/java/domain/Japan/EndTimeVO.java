package domain.Japan;

public class EndTimeVO {
    private final EndHourVO endHour;
    private final EndMinutesVO endMinutes;

    public EndTimeVO(String end) {
        this.endHour = new EndHourVO(Integer.valueOf(end));
        this.endMinutes = new EndMinutesVO(Integer.valueOf(end));
    }

    public int getHour() {
        return endHour.getValue();
    }

    public int getMinutes() {
        return endMinutes.getValue();
    }

    public int getTotalMinutes() {
        return endHour.getValue() * 60 + endMinutes.getValue();
    }

}
