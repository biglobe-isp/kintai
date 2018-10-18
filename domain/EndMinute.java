package domain;

public class EndMinute {
    private final int endMinute;

    EndMinute(String endDate){
        this.endMinute = Integer.valueOf(endDate.substring(2, 4));
    }

    public int getEndMinuteInt() {
        return endMinute;
    }

    public String getEndMinuteStr() {
        return String.valueOf(endMinute);
    }

}