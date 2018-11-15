package refactor.domain;

//業務ロジックを書くところ
public class EndTime {

    private String endTime;
    private int endHour;
    private int endMinutes;

    public EndTime(String endTime) {
        this.endTime = endTime;
        this.endHour = Integer.valueOf(endTime.substring(0, 2));
        this.endMinutes = Integer.valueOf(endTime.substring(2, 4));
    }

    public String getEndTime() {
        return endTime;
    }

    public int getEndHour() {
        return endHour;
    }

    public int getEndMinutes() {
        return endMinutes;
    }


}
