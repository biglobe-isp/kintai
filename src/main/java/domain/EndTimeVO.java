package domain;

public class EndTimeVO {
    private final String end;
    private final int endHour;
    private final int endMinute;

    public EndTimeVO(String end) {
        this.end = end;
        this.endHour = Integer.valueOf(end.substring(0, 2));
        this.endMinute = Integer.valueOf(end.substring(2, 4));
    }

    public String getEnd() {
        return end;
    }

    public int getEndHour() {
        return endHour;
    }

    public int getEndMinute() {
        return endMinute;
    }

    public int getEndTotalMinute() {
        return endHour * 60 + endMinute;
    }

    //    public int getBreakTime() {
//        int breakTime;
//        return 0;
//    }
}
