package datasource;

public class OverWorkTimeCalculator {

    public int calcDayOverWorkMinute(int dayWorkMinute){
        return Math.max(dayWorkMinute - 8 * 60, 0);
    }

}
