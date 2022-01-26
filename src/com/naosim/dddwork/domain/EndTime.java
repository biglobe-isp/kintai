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
    public String toEndTimeFormat() {
        return String.format("%02d", this.endHour) + "" + String.format("%02d", this.endMinute);
    }

}
