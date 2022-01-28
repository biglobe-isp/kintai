package src.com.naosim.dddwork.domain;

public class EndTime {
    private final int endHour;
    private final int endMinute;

    public EndTime(int endH, int endM) {
        this.endHour = endH;
        this.endMinute = endM;
    }

    public int getHour() { return this.endHour ; }
    public int getMinute() {
        return this.endMinute ;
    }

}
