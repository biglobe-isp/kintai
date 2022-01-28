package src.com.naosim.dddwork.domain;

public class StartTime {
    private final int startHour;
    private final int startMinute;

    public StartTime(int startH, int startM) {
        this.startHour = startH;
        this.startMinute = startM;
    }

    public int getHour() {
        return this.startHour ;
    }
    public int getMinute() {
        return this.startMinute ;
    }
}
