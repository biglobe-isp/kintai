package src.com.naosim.dddwork.domain;

public class EndTime {
    private final int endHour;
    private final int endMinute;

    public EndTime(int endH, int endM) {
        this.endHour = endH;
        this.endMinute = endM;
    }

    public int getEndH() {
        return this.endHour ;
    }
    public int getEndM() {
        return this.endMinute ;
    }
    public String toString() {
        return String.format("%02d%02d", this.endHour,this.endMinute);
    }

}
