package refactor.domain;

//業務ロジックを書くところ
public class StartTime {
    private String startTime;
    private int startHour;
    private int startMinutes;

    public StartTime(String startTime) {
        this.startTime = startTime;
        this.startHour = Integer.valueOf(startTime.substring(0, 2));
        this.startMinutes = Integer.valueOf(startTime.substring(2, 4));
    }

    public String getStartTime() {
        return startTime;
    }

    public int getStartHour() {
        return startHour;
    }

    public int getStartMinutes() {
        return startMinutes;
    }

}
