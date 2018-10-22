package domain;

public class EndTimeVO {
    private final EndVO end;
    private final EndHourVO endHour;
    private final EndMinutesVO endMinutes;

    public EndTimeVO(EndVO end, EndHourVO endHour, EndMinutesVO endMinutes) {
        this.end = end;
        this.endHour = endHour;
        this.endMinutes = endMinutes;
    }

    public String getValue() {
        return end.getValue();
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
