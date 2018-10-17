package domain;

public class StartTimeVO {
    private final String start;
    private final int startHour;
    private final int startMinute;

    public StartTimeVO(String start) {
        this.start = start;
        this.startHour = Integer.valueOf(start.substring(0, 2));
        this.startMinute = Integer.valueOf(start.substring(2, 4));
    }

    public String getStart() {
        return start;
    }

    public int getStartHour() {
        return startHour;
    }

    public int getStartMinute() {
        return startMinute;
    }

    public int getStartTotalMinute() {
        return startHour * 60 + startMinute;
    }
}
