package src.com.naosim.dddwork.domain;

public class StartTime {
    private final int startHour;
    private final int startMinute;

    public StartTime(int startH, int startM) {
        this.startHour = startH;
        this.startMinute = startM;
    }

    public int getStartH() {
        return this.startHour ;
    }
    public int getStartM() {
        return this.startMinute ;
    }
    public String toStartTimeFormat() {
        return String.format("%02d", this.startHour) + "" +  String.format("%02d", this.startMinute) ;
    }
}
