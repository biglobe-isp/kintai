package domain;

public class EndMinute {
    private final int endMinute;

    EndMinute(String endDate){
        this.endMinute = Integer.valueOf(endDate.substring(2, 4));
    }

    public int getEndMinute() {
        return endMinute;
    }
}