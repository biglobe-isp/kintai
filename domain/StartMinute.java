package domain;

public class StartMinute {
    private final int startMinute;

    StartMinute(String startDate){
        this.startMinute = Integer.valueOf(startDate.substring(2, 4));
    }

    public int getStartMinute() {
        return startMinute;
    }
}